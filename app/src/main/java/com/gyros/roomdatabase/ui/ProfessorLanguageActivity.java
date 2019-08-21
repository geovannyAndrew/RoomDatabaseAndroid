package com.gyros.roomdatabase.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.gyros.roomdatabase.R;
import com.gyros.roomdatabase.db.database.AppDatabase;
import com.gyros.roomdatabase.db.entity.Language;
import com.gyros.roomdatabase.db.entity.Professor;
import com.gyros.roomdatabase.db.entity.ProfessorLanguage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Completable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class ProfessorLanguageActivity extends AppCompatActivity {

    public static final String TAG = "ProfessorsLanguagesActi";

    @BindView(R.id.editIdProfessor)
    EditText editIdProfessor;

    @BindView(R.id.editIdLanguage)
    EditText editIdLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_language);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.buttonSaveProfessorLanguage)
    public void onSaveProfessorLanguage(){
        int idProfessor = Integer.parseInt(editIdProfessor.getText().toString());
        int idLanguage  = Integer.parseInt(editIdLanguage.getText().toString());
        ProfessorLanguage professorLanguage = new ProfessorLanguage();
        professorLanguage.setProfessorId(idProfessor);
        professorLanguage.setLanguageId(idLanguage);
        saveProfessorLanguage(professorLanguage);
    }

    private void saveProfessorLanguage(ProfessorLanguage professorLanguage){
        Completable.fromAction(() -> AppDatabase.getAppDatabase(ProfessorLanguageActivity.this)
                .professorLanguageDao().insert(professorLanguage)).subscribeOn(Schedulers.io()).subscribe();
    }

    @OnClick(R.id.buttonGetLanguagesByProfessor)
    public void onGetLanguagesByProfessor(){
        int idProfessor = Integer.parseInt(editIdProfessor.getText().toString());
        getLanguagesByProfessor(idProfessor);
    }

    private void getLanguagesByProfessor(int idProfessor){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                List<Language> languages = AppDatabase.getAppDatabase(ProfessorLanguageActivity.this)
                        .professorLanguageDao().getLanguagesByProfessor(idProfessor);
                for (Language language: languages){
                    Log.d(TAG,"Language id: "+language.getId());
                }
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    @OnClick(R.id.buttonGetProfessorByLanguages)
    public void onGetProfessorsByLanguages(){
        int idLanguage  = Integer.parseInt(editIdLanguage.getText().toString());
        getProfessorsByLanguage(idLanguage);
    }

    private void getProfessorsByLanguage(int idLanguage){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                List<Professor> professors = AppDatabase.getAppDatabase(ProfessorLanguageActivity.this)
                        .professorLanguageDao().getProfessorsByLanguage(idLanguage);
                for(Professor professor: professors){
                    Log.d(TAG, "Professor Id: "+professor.getId()+" name: "+professor.getName());
                }
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }


    @OnClick(R.id.buttonGetAllProfessorsLanguages)
    public void onGetAllProfessorsLanguages(){
        getAllProfessorsLanguages();
    }

    private void getAllProfessorsLanguages(){
        Completable.fromAction(() -> {
            List<ProfessorLanguage> professorsLanguages = AppDatabase.getAppDatabase(ProfessorLanguageActivity.this)
                    .professorLanguageDao().getAllProfessorsLanguages();
            for(ProfessorLanguage professorLanguage: professorsLanguages){
                Log.d(TAG, "Professor Id: "+professorLanguage.getProfessorId()+" , LanguageId: "+professorLanguage.getLanguageId());
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }
}
