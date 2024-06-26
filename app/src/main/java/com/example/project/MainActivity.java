package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.InflateException;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button btnProblem, btnSetting, btnRecord;

        btnProblem= findViewById(R.id.problemBtn);
        btnProblem.setOnClickListener(view -> {
//            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
//            View problemlist = getLayoutInflater().inflate(R.layout.problemlist,null);
//            dlg.setView(problemlist);

            Intent chapterActivity = new Intent(getApplicationContext(),com.example.project.chapter.class);

            startActivity(chapterActivity);
//
//            Button btnWordCard, btnChapter, btnWrongAnswer;
//
//            btnWordCard = problemlist.findViewById(R.id.btnWordCard);
//            btnWordCard.setOnClickListener(v ->{
//            });

//            dlg.show();

        });

        btnSetting = findViewById(R.id.settingBtn);
        btnSetting.setOnClickListener(v -> {
            Intent settingActivity = new Intent(getApplicationContext(),com.example.project.settingActivity.class);
            startActivity(settingActivity);
        });

        btnRecord = findViewById(R.id.recordBtn);
        btnRecord.setOnClickListener(v -> {
            Intent recordActivity = new Intent(getApplicationContext(),com.example.project.recordActivity.class);
            startActivity(recordActivity);
        });
    }
}