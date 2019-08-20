package com.gyros.roomdatabase.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.gyros.roomdatabase.R;
import com.gyros.roomdatabase.db.database.AppDatabase;
import com.gyros.roomdatabase.db.entity.Course;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class CourseActivity extends AppCompatActivity {

    @BindView(R.id.courseActivityIdProfesor)
    EditText editIdProfessor;

    @BindView(R.id.courseActivityName)
    EditText editCourseName;

    @BindView(R.id.courseActivityIdDuracion)
    EditText editCourseDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.courseActivityBtSalvar)
    public void onSaveCourse(){
        int duration = Integer.parseInt(editCourseDuration.getText().toString());
        int professorId = Integer.parseInt(editIdProfessor.getText().toString());
        Course course = new Course();
        course.setDuration(duration);
        course.setName(editCourseName.getText().toString());
        course.setProfessorId(professorId);
        Completable.fromAction(() -> AppDatabase.getAppDatabase(CourseActivity.this)
                .courseDao().insert(course)).subscribeOn(Schedulers.io()).subscribe();
    }
}
