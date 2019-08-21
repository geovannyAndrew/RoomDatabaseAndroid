package com.gyros.roomdatabase.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.gyros.roomdatabase.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LanguagesActivity extends AppCompatActivity {

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

    }

    @OnClick(R.id.buttonGetAllLanguages)
    public void onGetAllLanguages(){

    }

    @OnClick(R.id.buttonUpdateLanguage)
    public void onUpdateLanguage(){

    }

    @OnClick(R.id.buttonDeleteLanguage)
    public void onDeleteLanguage(){
        
    }
}
