package com.gyros.roomdatabase.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.gyros.roomdatabase.R;
import com.gyros.roomdatabase.db.database.AppDatabase;
import com.gyros.roomdatabase.db.entity.Professor;

import java.util.List;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfessorActivity extends AppCompatActivity {

    public static final String TAG = "ProfessorActivity";

    @BindView(R.id.editTextName)
    EditText editName;

    @BindView(R.id.editTextEmail)
    EditText editEmail;

    CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.buttonSave)
    public void onSave(){
        String name = editName.getText().toString();
        String email = editEmail.getText().toString();

        Professor professor = new Professor();
        professor.setEmail(email);
        professor.setName(name);
        saveProfessor(professor);
    }


    @OnClick(R.id.buttonRead)
    public void onGetAllProfessors(){
        getAllProfessors();
    }


    @OnClick(R.id.buttonSearchByName)
    public void onGetProfessorsByName(){
        String name = editName.getText().toString();
        onGetProffesorByName(name);
    }

    public void onGetProffesorByName(final String name){
        Observable<List<Professor>> observable = Observable.fromCallable(new Callable<List<Professor>>() {
            @Override
            public List<Professor> call() throws Exception {
                return AppDatabase.getAppDatabase(ProfessorActivity.this).professorDao().findProfessorsByName(name);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<List<Professor>>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposables.add(d);
            }

            @Override
            public void onNext(List<Professor> professors) {
                Log.d(TAG,"onNext: "+professors.size());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"onComplete");
            }
        });
    }

    private void saveProfessor(Professor professor){
        Observable<Professor> observable = Observable.fromCallable(() -> {
            AppDatabase.getAppDatabase(ProfessorActivity.this).professorDao().insertProfessor(professor);
            return professor;
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<Professor>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposables.add(d);
                Log.d(TAG,"onSubscribe");
            }

            @Override
            public void onNext(Professor professor) {
                Log.d(TAG,"onNext");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"onComplete");
            }
        });
    }

    private void getAllProfessors(){
        Observable<List<Professor>> observable = Observable.fromCallable(() -> AppDatabase.getAppDatabase(ProfessorActivity.this).professorDao().getAllProfessors()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<List<Professor>>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposables.add(d);
            }

            @Override
            public void onNext(List<Professor> professors) {
                Log.d(TAG,"onNext: "+professors.size());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"onComplete");
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }
}
