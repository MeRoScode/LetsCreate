package com.meros.letscreate.Fragments.Calender;

import android.content.ContentValues;

public class TackModel {

    public static final String ID = "id";
    public static final String CAT_ID = "cat_id";
    public static final String URGENCY = "urgency";
    public static final String DATE = "date";
    public static final String TIME = "time";
    public static final String TAG = "tag";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String IS_FINISHED = "is_finished";

    public ContentValues getContentValue(){
        ContentValues values = new ContentValues();
        values.put(CAT_ID,cat_id);
        values.put(URGENCY,urgency);
        values.put(DATE,date);
        values.put(TIME,time);
        values.put(TAG,tag);
        values.put(TITLE,title);
        values.put(DESCRIPTION,description);
        if (isFinished){
            values.put(IS_FINISHED,1);
        }else{
            values.put(IS_FINISHED,0);
        }
        return values;
    }

    private int id,cat_id,urgency;
    private String date,time,tag, title,description;
    private boolean isFinished;

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public int getUrgency() {
        return urgency;
    }

    public void setUrgency(int urgency) {
        this.urgency = urgency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
