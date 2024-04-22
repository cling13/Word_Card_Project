package com.example.project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    Context context;

    public DBHelper(@Nullable Context context) throws IOException {
        super(context, "Problem", null, 1);
        this.context=context;

        copyDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void copyDatabase() throws IOException{
        File dbFile = context.getDatabasePath("Problem");

            InputStream inputStream = context.getAssets().open("Problem.db");
            OutputStream outputStream = new FileOutputStream(dbFile);

            byte[] buffer = new byte[1024];
            int length;
            while((length=inputStream.read(buffer))>0){
                outputStream.write(buffer,0,length);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();
            Log.d("tag","success");

    }

    public List<Problem> getDataItems(ArrayList<String> studychapter){
        List<Problem> datalist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select * from Problem where chapter in (\"";
        for(String tmp:studychapter)
        {
            sql += tmp + "\", \"";
        }
        sql=sql.substring(0,sql.length()-3);
        sql += ")";

        Log.d("sql",sql);

        Cursor cursor=db.rawQuery(sql,null);

        while(cursor.moveToNext()){
            Problem dataItem = new Problem(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
            datalist.add(dataItem);
        }

        cursor.close();
        db.close();

        return datalist;
    }

    public ArrayList<String> chapterCnt()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select distinct chapter from Problem";
        Cursor cursor=db.rawQuery(sql,null);

        ArrayList<String> chapterName = new ArrayList<>();

        while(cursor.moveToNext()) {
            String name = cursor.getString(0);
            chapterName.add(name);
        }
        cursor.close();
        db.close();

        return chapterName;
    }

}
