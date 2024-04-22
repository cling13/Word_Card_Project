package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Intent intent = getIntent();
        ArrayList<String> chapters = intent.getStringArrayListExtra("chapter");

        try {
            wordCard(chapters);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        Button btnAns = (Button) findViewById(R.id.btnAns);
//        btnAns.setOnClickListener(v -> {
//            btnAns.setVisibility(View.INVISIBLE);
//        });
    }

    void wordCard(ArrayList<String> chapters) throws IOException {
        textQue=findViewById(R.id.textQue);
        textAns=findViewById(R.id.textAns);

        List<Problem> dataList=dbHelper.getDataItems(chapters);
        Collections.shuffle(dataList);

        textQue.setText(dataList.get(0).question);
        textAns.setText(dataList.get(0).answer);
    }


}