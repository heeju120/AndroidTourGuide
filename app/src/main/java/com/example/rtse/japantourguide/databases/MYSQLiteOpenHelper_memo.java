package com.example.rtse.japantourguide.databases;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RTSE on 2015-12-11.
 */
public class MYSQLiteOpenHelper_memo extends SQLiteOpenHelper {


    public MYSQLiteOpenHelper_memo(Context context, String name,
                                       CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        String sql = "create table Memo" +
                "(id integer primary key autoincrement, "
                + "Key_title text, "  + "Key_context text)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists park;";
        onCreate(db); // table 다시 만들기
    }



}
