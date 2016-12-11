package com.clempaj.beerconsumption.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clempaj.beerconsumption.R;
import com.clempaj.beerconsumption.db.BeerContract;
import com.clempaj.beerconsumption.db.ConsumptionDbHelper;

import java.util.Date;

public class AddConsumptionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_consumption);

        Intent intent = getIntent();
        long beerId = SelectBeerActivity.getBeerId(intent);
        Date date = SelectDateActivity.getDate(intent);
        int volume = SelectVolumeActivity.getVolume(intent);

        boolean succeded = insertBeerDb(beerId, date, volume);

        Intent leave = new Intent(this, MainActivity.class);
        MainActivity.setSucceded(intent, succeded);
        startActivity(leave);
    }

    private boolean insertBeerDb(long beerId, Date date, int volume) {
        ConsumptionDbHelper helper = new ConsumptionDbHelper(getApplicationContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(BeerContract.ConsumptionEntry.COLUMN_NAME_BEER_ID, beerId);
        content.put(BeerContract.ConsumptionEntry.COLUMN_NAME_DATE, date.getTime());
        content.put(BeerContract.ConsumptionEntry.COLUMN_NAME_VOLUME, volume);
        long id = db.insert(BeerContract.ConsumptionEntry.TABLE_NAME, null, content);
        return id != -1;
    }
}
