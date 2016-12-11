package com.clempaj.beerconsumption.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.clempaj.beerconsumption.data.Beer;
import com.clempaj.beerconsumption.data.Consumption;
import com.clempaj.beerconsumption.db.BeerContract.BeerEntry;
import com.clempaj.beerconsumption.db.BeerContract.ConsumptionEntry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsumptionDataAccess {
    private static final String QUERY_BEER_NAME_AND_CONSUMPION =
            "SELECT * FROM " + BeerEntry.TABLE_NAME + " INNER JOIN " + ConsumptionEntry.TABLE_NAME
                    + " ON " + BeerEntry.TABLE_NAME + "." + BeerEntry._ID + " = " + ConsumptionEntry.TABLE_NAME + "." + ConsumptionEntry.COLUMN_NAME_BEER_ID
                    + " ORDER BY " + ConsumptionEntry.COLUMN_NAME_DATE + " DESC";

    private DbHelper helper;

    public ConsumptionDataAccess(Context context) {
        helper = new DbHelper(context);
    }

    public boolean addConsumption(long beerId, Date date, int volume) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(ConsumptionEntry.COLUMN_NAME_BEER_ID, beerId);
        content.put(ConsumptionEntry.COLUMN_NAME_DATE, date.getTime());
        content.put(ConsumptionEntry.COLUMN_NAME_VOLUME, volume);
        return db.insert(ConsumptionEntry.TABLE_NAME, null, content) != -1;
    }

    public void deleteConsumptions() {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(ConsumptionEntry.TABLE_NAME, null, null);
    }

    public List<Consumption> getConsumptions() {
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery(QUERY_BEER_NAME_AND_CONSUMPION, new String[]{});

        List<Consumption> consumptions = new ArrayList<>();
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            long beerId = cursor.getLong(cursor.getColumnIndexOrThrow(BeerEntry._ID));
            String beerName = cursor.getString(cursor.getColumnIndexOrThrow(BeerEntry.COLUMN_NAME_NAME));
            double alcohol = cursor.getDouble(cursor.getColumnIndexOrThrow(BeerEntry.COLUMN_NAME_ALCOHOL));
            int volume = cursor.getInt(cursor.getColumnIndexOrThrow(ConsumptionEntry.COLUMN_NAME_VOLUME));
            long dateLong = cursor.getLong(cursor.getColumnIndexOrThrow(ConsumptionEntry.COLUMN_NAME_DATE));

            Date date = new Date();
            date.setTime(dateLong);

            Beer beer = new Beer(beerId, beerName, alcohol);

            Consumption cons = new Consumption(date, volume, beer);
            consumptions.add(cons);
            cursor.moveToNext();
        }
        return consumptions;
    }
}
