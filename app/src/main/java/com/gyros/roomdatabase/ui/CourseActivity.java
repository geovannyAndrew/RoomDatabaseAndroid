package com.gyros.roomdatabase.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.gyros.roomdatabase.R;
import com.gyros.roomdatabase.db.database.AppDatabase;
import com.gyros.roomdatabase.db.entity.Course;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class CourseActivity extends AppCompatActivity {

    public static final String TAG = "CourseActivity";

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


    @OnClick(R.id.courseActivityBtLeerCursosPorProfesor)
    public void onGetCoursesByProfessor(){
        int professorId = Integer.parseInt(editIdProfessor.getText().toString());
        Completable.fromAction(() -> {
            List<Course> courses = AppDatabase.getAppDatabase(CourseActivity.this)
                    .courseDao().findCoursesByProfessor(professorId);

            Log.d(TAG,"Number courses: "+courses.size());
        }).subscribeOn(Schedulers.io()).subscribe();
    }


    @OnClick(R.id.courseActivityBtActualizarCursosPorId)
    public void onUpdateCourseById(){
        Course course = new Course();
        course.setId(1);
        course.setDuration(60);
        course.setName("Name Course Modified");
        course.setProfessorId(1);
        Completable.fromAction(() -> AppDatabase.getAppDatabase(CourseActivity.this)
                .courseDao().updateCourseById(course)).subscribeOn(Schedulers.io()).subscribe();
    }
}
