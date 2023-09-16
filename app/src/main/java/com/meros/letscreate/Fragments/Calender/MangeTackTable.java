package com.meros.letscreate.Fragments.Calender;

import static com.meros.letscreate.Constants.TACK_TB;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.meros.letscreate.MyDb;

import java.util.ArrayList;

public class MangeTackTable extends MyDb {
    public MangeTackTable(Context context) {
        super(context);
    }

    public long insert(TackModel tackModel){
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(TACK_TB,null,tackModel.getContentValue());
        db.close();
        return id;
    }


    @SuppressLint("Range")
    public ArrayList<TackModel> getAllTacks(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<TackModel> tacks = null;
        Cursor cursor = db.rawQuery(" SELECT * FROM " + TACK_TB,new String[]{});
        if (cursor.moveToFirst()){
            tacks = new ArrayList<>();
            do {
                TackModel tackModel = new TackModel();
                tackModel.setId(cursor.getInt(cursor.getColumnIndex(TackModel.ID)));
                tackModel.setCat_id(cursor.getInt(cursor.getColumnIndex(TackModel.CAT_ID)));
                tackModel.setUrgency(cursor.getInt(cursor.getColumnIndex(TackModel.URGENCY)));
                tackModel.setDate(cursor.getString(cursor.getColumnIndex(TackModel.DATE)));
                tackModel.setTime(cursor.getString(cursor.getColumnIndex(TackModel.TIME)));
                tackModel.setTitle(cursor.getString(cursor.getColumnIndex(TackModel.TITLE)));
                tackModel.setDescription(cursor.getString(cursor.getColumnIndex(TackModel.DESCRIPTION)));
                tackModel.setTag(cursor.getString(cursor.getColumnIndex(TackModel.TAG)));
                tackModel.setFinished(cursor.getInt(cursor.getColumnIndex(TackModel.IS_FINISHED)) == 1);
                tacks.add(tackModel);
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return tacks;
    }

    @SuppressLint("Range")
    public ArrayList<TackModel> getTacks(String sql,String[] selection){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<TackModel> tacks = null;
        Cursor cursor = db.rawQuery(sql, selection);
        if (cursor.moveToFirst()){
            tacks = new ArrayList<>();
            do {
                TackModel tackModel = new TackModel();
                tackModel.setId(cursor.getInt(cursor.getColumnIndex(TackModel.ID)));
                tackModel.setCat_id(cursor.getInt(cursor.getColumnIndex(TackModel.CAT_ID)));
                tackModel.setUrgency(cursor.getInt(cursor.getColumnIndex(TackModel.URGENCY)));
                tackModel.setDate(cursor.getString(cursor.getColumnIndex(TackModel.DATE)));
                tackModel.setTime(cursor.getString(cursor.getColumnIndex(TackModel.TIME)));
                tackModel.setTitle(cursor.getString(cursor.getColumnIndex(TackModel.TITLE)));
                tackModel.setDescription(cursor.getString(cursor.getColumnIndex(TackModel.DESCRIPTION)));
                tackModel.setTag(cursor.getString(cursor.getColumnIndex(TackModel.TAG)));
                tackModel.setFinished(cursor.getInt(cursor.getColumnIndex(TackModel.IS_FINISHED)) == 1);
                tacks.add(tackModel);
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return tacks;
    }

}

