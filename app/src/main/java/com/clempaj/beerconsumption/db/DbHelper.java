package com.clempaj.beerconsumption.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class DbHelper extends SQLiteOpenHelper {
    protected static final String TEXT_TYPE = " TEXT";
    protected static final String INT_TYPE = " INTEGER";
    protected static final String DOUBLE_TYPE = " REAL";
    protected static final String COMMA_SEP = ",";
    protected static final String PRIMARY_KEY = " PRIMARY KEY";
    protected static final String UNIQUE_KEY = " UNIQUE";

    public static final String DATABASE_NAME = "Beer.db";

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
}