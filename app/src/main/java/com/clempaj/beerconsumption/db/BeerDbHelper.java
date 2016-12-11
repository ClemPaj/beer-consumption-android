package com.clempaj.beerconsumption.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BeerDbHelper extends DbHelper {
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + BeerContract.BeerEntry.TABLE_NAME + " (" +
                    BeerContract.BeerEntry._ID + INT_TYPE + PRIMARY_KEY + COMMA_SEP +
                    BeerContract.BeerEntry.COLUMN_NAME_NAME + TEXT_TYPE + UNIQUE_KEY + COMMA_SEP +
                    BeerContract.BeerEntry.COLUMN_NAME_ALCOHOL + DOUBLE_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + BeerContract.BeerEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;

    public BeerDbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
