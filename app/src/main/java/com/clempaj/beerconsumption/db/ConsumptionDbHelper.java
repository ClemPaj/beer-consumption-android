package com.clempaj.beerconsumption.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ConsumptionDbHelper extends DbHelper {
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + BeerContract.ConsumptionEntry.TABLE_NAME + " (" +
                    BeerContract.ConsumptionEntry._ID + INT_TYPE + PRIMARY_KEY + COMMA_SEP +
                    BeerContract.ConsumptionEntry.COLUMN_NAME_DATE + INT_TYPE + UNIQUE_KEY + COMMA_SEP +
                    BeerContract.ConsumptionEntry.COLUMN_NAME_BEER_ID + INT_TYPE + COMMA_SEP +
                    BeerContract.ConsumptionEntry.COLUMN_NAME_VOLUME + INT_TYPE + COMMA_SEP +
                    "FOREIGN KEY(" + BeerContract.ConsumptionEntry.COLUMN_NAME_BEER_ID + ") REFERENCES " + BeerContract.BeerEntry.TABLE_NAME + "(" + BeerContract.BeerEntry._ID + ")" + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + BeerContract.ConsumptionEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;

    public ConsumptionDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
