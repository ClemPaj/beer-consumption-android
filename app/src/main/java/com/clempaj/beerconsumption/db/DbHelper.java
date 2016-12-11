package com.clempaj.beerconsumption.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.clempaj.beerconsumption.db.BeerContract.BeerEntry;
import com.clempaj.beerconsumption.db.BeerContract.ConsumptionEntry;

public class DbHelper extends SQLiteOpenHelper {
    protected static final String TEXT_TYPE = " TEXT";
    protected static final String INT_TYPE = " INTEGER";
    protected static final String DOUBLE_TYPE = " REAL";
    protected static final String COMMA_SEP = ",";
    protected static final String PRIMARY_KEY = " PRIMARY KEY";
    protected static final String UNIQUE_KEY = " UNIQUE";

    private static final String CONSUMPTION_SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ConsumptionEntry.TABLE_NAME + " (" +
                    ConsumptionEntry._ID + INT_TYPE + PRIMARY_KEY + COMMA_SEP +
                    ConsumptionEntry.COLUMN_NAME_DATE + INT_TYPE + UNIQUE_KEY + COMMA_SEP +
                    ConsumptionEntry.COLUMN_NAME_BEER_ID + INT_TYPE + COMMA_SEP +
                    ConsumptionEntry.COLUMN_NAME_VOLUME + INT_TYPE + COMMA_SEP +
                    "FOREIGN KEY(" + ConsumptionEntry.COLUMN_NAME_BEER_ID + ") REFERENCES " +
                    BeerEntry.TABLE_NAME + "(" + BeerEntry._ID + ") ON UPDATE CASCADE" +
                    " ON DELETE CASCADE" +
                    " )";

    private static final String CONSUMPTION_SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ConsumptionEntry.TABLE_NAME;

    private static final String BEER_SQL_CREATE_ENTRIES =
            "CREATE TABLE " + BeerEntry.TABLE_NAME + " (" +
                    BeerEntry._ID + INT_TYPE + PRIMARY_KEY + COMMA_SEP +
                    BeerEntry.COLUMN_NAME_NAME + TEXT_TYPE + UNIQUE_KEY + COMMA_SEP +
                    BeerEntry.COLUMN_NAME_ALCOHOL + DOUBLE_TYPE + " )";

    private static final String BEER_SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + BeerEntry.TABLE_NAME;

    public static final String DATABASE_NAME = "Beer.db";
    public static final int DB_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BEER_SQL_CREATE_ENTRIES);
        db.execSQL(CONSUMPTION_SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        return;
    }

    @Override
    public void onConfigure(SQLiteDatabase db){
        db.setForeignKeyConstraintsEnabled(true);
    }
}