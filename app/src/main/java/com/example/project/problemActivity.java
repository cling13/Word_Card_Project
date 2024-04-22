package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class problemActivity extends AppCompatActivity{

    TextView textQue,textAns;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);

        try {
            dbHelper = new DBHelper(getApplicationContext());
        } catch (IOException e) {}

        Intent intent = getIntent();
        String studymode = intent.getStringExtra("studyMode");

        switch (studymode)
        {
            case "wordCard":
                wordCard();
                break;
            case "chapter":
                chapter();
                break;
            case "wrongAnswer":
                wrongAnswer();
                break;
        }
    }

    void wordCard() {
        textQue=findViewById(R.id.textQue);
        textAns=findViewById(R.id.textAns);

        List<Problem> dataList=dbHelper.getDataItems();

        textQue.setText(dataList.get(0).question);
        textAns.setText(dataList.get(0).answer);
    }

    void chapter()
    {

    }

    void wrongAnswer()
    {

    }


}