package com.gyros.roomdatabase.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.gyros.roomdatabase.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfessorLanguageActivity extends AppCompatActivity {

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

    }

    @OnClick(R.id.buttonGetProfessor)
    public void getProfessor(){

    }

    @OnClick(R.id.buttonGetLanguage)
    public void getLanguage(){

    }
}
