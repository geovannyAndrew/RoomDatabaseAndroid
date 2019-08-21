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
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class ProfessorActivity extends AppCompatActivity {

    public static final String TAG = "ProfessorActivity";

    @BindView(R.id.editTextName)
    EditText editName;

    @BindView(R.id.editTextEmail)
    EditText editEmail;

    CompositeDisposable disposables = new CompositeDisposable();
    Professor professor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
        ButterKnife.bind(this);
        //creatingProfessorModifiable();
    }

    @OnClick(R.id.buttonRxJust)
    public void onModifyProfessor(){
        professor.setName("Otro nombre");
    }


    @OnClick(R.id.buttonSaveLanguage)
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

    @OnClick(R.id.buttonSearchById)
    public void onGetProfessorById(){
        int id = 2;
        getProfessorById(id);
    }

    @OnClick(R.id.buttonUpdate)
    public void updateProfessor(){
        Professor professor = new Professor();
        professor.setId(2);
        professor.setName("Name Modified");
        professor.setEmail("Email Modified");
        updateProfessorById(professor);
    }

    @OnClick(R.id.buttonDeleteById)
    public void deleteProfessor(){
        Professor professor = new Professor();
        professor.setId(2);
        deleteProfessorById(professor);
    }

    @OnClick(R.id.buttonDelete)
    public void onDeleteAllProfessors(){
        deleteAllProfessors();
    }

    private void deleteAllProfessors(){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                AppDatabase.getAppDatabase(ProfessorActivity.this)
                        .professorDao().deleteAllProfessors();
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    private void deleteProfessorById(Professor professor){
        Observable<Professor> observable = Observable.fromCallable(new Callable<Professor>() {
            @Override
            public Professor call() throws Exception {
                AppDatabase.getAppDatabase(ProfessorActivity.this).professorDao().deleteProfessorById(professor);
                return professor;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<Professor>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposables.add(d);
            }

            @Override
            public void onNext(Professor professor) {
                Log.d(TAG,"onNext: Deleted: "+professor.getName());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"onComplete: ");
            }
        });
    }

    private void updateProfessorById(Professor professor){
        Observable<Professor> observable = Observable.fromCallable(new Callable<Professor>() {
            @Override
            public Professor call() throws Exception {
                AppDatabase.getAppDatabase(ProfessorActivity.this)
                        .professorDao().updateProfessorById(professor);
                return professor;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<Professor>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposables.add(d);
            }

            @Override
            public void onNext(Professor professor) {
                Log.d(TAG,"onNext: Name: "+professor.getName());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"onComplete: ");
            }
        });
    }


    private void getProfessorById(int id){
        Observable<Professor> observable = Observable.fromCallable(new Callable<Professor>() {
            @Override
            public Professor call() throws Exception {
                return AppDatabase.getAppDatabase(ProfessorActivity.this).professorDao().findProfessorById(id);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<Professor>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposables.add(d);
            }

            @Override
            public void onNext(Professor professor) {
                Log.d(TAG,"onNext: Name: "+professor.getName());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"onError: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"onComplete: ");
            }
        });
    }

    private void onGetProffesorByName(final String name){
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


    private void creatingProfessorModifiable(){
        professor = new Professor();
        professor.setId(1);
        professor.setName("Name");
        professor.setEmail("otro@email.com");
        Observable.just(professor)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Professor>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                        Log.d(TAG,"onSubscribe ");
                    }

                    @Override
                    public void onNext(Professor professor) {
                        Log.d(TAG,"Professor "+professor.getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"onError ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG,"onComplete ");
                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }
}
