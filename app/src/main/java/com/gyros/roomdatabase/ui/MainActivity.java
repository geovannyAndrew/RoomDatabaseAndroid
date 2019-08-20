package com.gyros.roomdatabase.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.gyros.roomdatabase.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.mainActivityProfessor)
    public void goToProfessorActivity(){
        Intent intent = new Intent(this,ProfessorActivity.class);
        startActivity(intent);
    }



}