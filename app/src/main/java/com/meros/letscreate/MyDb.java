package com.meros.letscreate;

import static com.meros.letscreate.Constants.TACK_TB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.meros.letscreate.Fragments.Calender.TackModel;

public class MyDb extends SQLiteOpenHelper {

    public static final String Db_NAME = "lets_create_db";
    public static final int Db_VERSION = 1;
    public MyDb(Context context) {
        super(context, Db_NAME, null, Db_VERSION);
    }

    public String createTackTable = " CREATE TABLE IF NOT EXISTS "+ TACK_TB + "(" +
            "'" + TackModel.ID  + "' INTEGER PRIMARY KEY ," +
            "'" + TackModel.CAT_ID  + "' INTEGER ," +
            "'" + TackModel.URGENCY  + "' INTEGER ," +
            "'" + TackModel.DATE  + "' TEXT ," +
            "'" + TackModel.TIME  + "' TEXT ," +
            "'" + TackModel.TITLE  + "' TEXT ," +
            "'" + TackModel.DESCRIPTION  + "' TEXT ," +
            "'" + TackModel.TAG  + "' TEXT ," +
            "'" + TackModel.IS_FINISHED  + "' INTEGER " +
            ")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTackTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + TACK_TB);
        onCreate(db);
    }
}
