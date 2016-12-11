package com.clempaj.beerconsumption.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.clempaj.beerconsumption.data.Beer;
import com.clempaj.beerconsumption.db.BeerContract.BeerEntry;

import java.util.ArrayList;
import java.util.List;

public class BeerDataAccess {
    private DbHelper helper;

    public BeerDataAccess(Context context) {
        helper = new DbHelper(context);
    }

    public boolean addBeer(String name, double alcohol) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(BeerEntry.COLUMN_NAME_NAME, name);
        content.put(BeerEntry.COLUMN_NAME_ALCOHOL, alcohol);
        return db.insert(BeerEntry.TABLE_NAME, null, content) != -1;
    }

    public void deleteBeers() {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(BeerEntry.TABLE_NAME, null, null);
    }

    public List<Beer> getBeers() {
        Cursor cursor = getBeerCursor();

        List<Beer> beers = new ArrayList<>();
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(BeerEntry._ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(BeerEntry.COLUMN_NAME_NAME));
            double alcohol = cursor.getDouble(cursor.getColumnIndexOrThrow(BeerEntry.COLUMN_NAME_ALCOHOL));
            Beer beer = new Beer(id, name, alcohol);
            beers.add(beer);
            cursor.moveToNext();
        }
        cursor.close();

        return beers;
    }

    private Cursor getBeerCursor() {
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] projection = {BeerEntry._ID, BeerEntry.COLUMN_NAME_NAME, BeerEntry.COLUMN_NAME_ALCOHOL};
        return db.query(
                BeerEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                BeerEntry.COLUMN_NAME_NAME + " ASC"       // The sort order
        );
    }
}
