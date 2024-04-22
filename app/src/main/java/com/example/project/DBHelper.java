package com.example.project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.ScriptGroup;
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
        super(context, "Information_processing_enginear", null, 1);
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
        File dbFile = context.getDatabasePath("Information_processing_enginear.db");
        if(!dbFile.exists())
        {
            InputStream inputStream = context.getAssets().open("Information_processing_enginear.db");
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
    }

    public List<Problem> getDataItems(){
        List<Problem> datalist = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try{
            db=getReadableDatabase();
            cursor=db.query("Problem",null,null,null,null,null,null);
            if(cursor!=null&&cursor.moveToFirst()){

                int numColumnIndex = cursor.getColumnIndex("chapter");
                int queColumnIndex = cursor.getColumnIndex("question");
                int ansColumnIndex = cursor.getColumnIndex("answer");

                if(numColumnIndex>=0&&queColumnIndex>=0&&ansColumnIndex>=0) {
                    do {
                        int num = cursor.getInt(numColumnIndex);
                        String que = cursor.getString(queColumnIndex);
                        String ans = cursor.getString(ansColumnIndex);
                        Problem dataItem = new Problem(num, que, ans);
                        datalist.add(dataItem);
                    } while (cursor.moveToNext());
                }
            }
        }catch (Exception e){

        }finally {
            if(cursor!=null){
                cursor.close();
            }
            if(db!=null){
                db.close();
            }
        }
        return datalist;
    }
}
