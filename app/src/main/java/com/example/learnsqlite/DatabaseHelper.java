package com.example.learnsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "tbl_student";

    public static final String _ID = "_id";
    public static final String KELAS = "kelas";
    public static final String NAMA = "nama";


    static final String DB_NAME = "CBD.DB";
    static final int DB_VERSION = 1;

    /*Query Table*/
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + KELAS + " TEXT NOT NULL, " + NAMA + " TEXT);";

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
