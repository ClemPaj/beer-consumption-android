package com.clempaj.beerconsumption.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clempaj.beerconsumption.R;
import com.clempaj.beerconsumption.db.ConsumptionDataAccess;

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

        ConsumptionDataAccess consumptionAccess = new ConsumptionDataAccess(getApplicationContext());
        consumptionAccess.addConsumption(beerId, date, volume);

        Intent leave = new Intent(this, MainActivity.class);
        startActivity(leave);
    }
}
