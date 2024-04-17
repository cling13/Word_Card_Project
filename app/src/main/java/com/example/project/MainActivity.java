package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnProblem, btnWord, btnSetting, btnRecord;

        btnProblem=(Button) findViewById(R.id.problemBtn);
        btnProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent problemActivity = new Intent(getApplicationContext(), com.example.project.problemActivity.class);
                startActivity(problemActivity);
            }
        });

        
    }
}