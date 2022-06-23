package edu.coltec.todoapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "Todo2.sqlite";
    private static final int DB_VERSION = 3;

    public AppDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CategoryDAO.CREATE_SCRIPT);
        db.execSQL(TaskDAO.CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CategoryDAO.CREATE_SCRIPT);
        db.execSQL(TaskDAO.CREATE_SCRIPT);
    }
}
