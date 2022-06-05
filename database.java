package com.example.task_71p;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    public database(@Nullable Context context) {
        super(context, data.DATABASE_NAME, null, data.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE " + data.TABLE_NAME + "(" + data.ID + " INTEGER PRIMARY KEY , " + data.CONDITION + " TEXT, " + data.NAME + " TEXT, " + data.PHONE + " TEXT, " + data.DESC + " TEXT, " + data.DATE + " TEXT, " + data.LOC + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS ";
        sqLiteDatabase.execSQL(DROP_USER_TABLE, new String[]{data.TABLE_NAME});

        onCreate(sqLiteDatabase);
    }

    public long insertNew(lostArticle newLostArticle){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(data.CONDITION, newLostArticle.Condition);
        contentValues.put(data.NAME, newLostArticle.Name);
        contentValues.put(data.PHONE, newLostArticle.Phone);
        contentValues.put(data.DESC, newLostArticle.Desc);
        contentValues.put(data.DATE, newLostArticle.Date);
        contentValues.put(data.LOC, newLostArticle.Loc);
        long newRowId = db.insert(data.TABLE_NAME, null, contentValues);
        db.close();
        return newRowId;
    }
}
