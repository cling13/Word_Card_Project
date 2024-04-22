package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.io.IOException;
import java.util.ArrayList;

public class chapter extends AppCompatActivity {

    boolean FirstTime = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        LinearLayout chapterList = findViewById(R.id.chapterList);
        CheckBox[] checkBoxList;

        if(FirstTime) {
            DBHelper dbHelper = null;
            try {
                dbHelper = new DBHelper(getApplicationContext());
            } catch (IOException e) {
            }

            ArrayList<String> chapterNames = dbHelper.chapterCnt();
            checkBoxList = new CheckBox[chapterNames.size()];

            for(int i=0; i<chapterNames.size(); i++)
            {
                checkBoxList[i]=new CheckBox(this);
                checkBoxList[i].setText(Integer.toString(i+1)+"장 "+chapterNames.get(i));
                checkBoxList[i].setTextSize(24);
                checkBoxList[i].setPadding(15,15,15,15);
                chapterList.addView(checkBoxList[i]);
            }

            FirstTime=false;
        } else {
            checkBoxList = new CheckBox[0];
        }

        Button btnSelectChapter = findViewById(R.id.btnChapter);
        btnSelectChapter.setOnClickListener(v -> {
            ArrayList<String> chapters = new ArrayList<>();
            for(int i=0; i<checkBoxList.length; i++)
            {
                if(checkBoxList[i].isChecked())
                {
                    chapters.add(checkBoxList[i].getText().toString().substring(3));
                }
            }

            Intent problemActivity = new Intent(this,problemActivity.class);
            problemActivity.putStringArrayListExtra("chapter",chapters);
            startActivity(problemActivity);
        });

    }
}