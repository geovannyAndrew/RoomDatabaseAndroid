package com.gyros.roomdatabase.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.gyros.roomdatabase.R;
import com.gyros.roomdatabase.db.database.AppDatabase;
import com.gyros.roomdatabase.db.entity.Language;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Completable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class LanguagesActivity extends AppCompatActivity {

    public static final String TAG = "LanguagesActivity";

    @BindView(R.id.editTextCourse)
    EditText editName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonSaveLanguage)
    public void onSave(){
        Language language = new Language();
        language.setName(editName.getText().toString());
        insertLanguage(language);
    }

    private void insertLanguage(Language language){
        Completable.fromAction(() -> AppDatabase.getAppDatabase(LanguagesActivity.this).languagesDao().insert(language)).subscribeOn(Schedulers.io()).subscribe();
    }

    @OnClick(R.id.buttonGetAllLanguages)
    public void onGetAllLanguages(){
        getAllLanguages();
    }

    private void getAllLanguages(){
        Completable.fromAction(() -> {
            List<Language> languages = AppDatabase.getAppDatabase(LanguagesActivity.this).languagesDao().getAllLanguages();
            for(Language language: languages){
                Log.d(TAG,"Language: "+language.getName());
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    @OnClick(R.id.buttonUpdateLanguage)
    public void onUpdateLanguage(){

    }

    @OnClick(R.id.buttonDeleteLanguage)
    public void onDeleteLanguage(){

    }
}
