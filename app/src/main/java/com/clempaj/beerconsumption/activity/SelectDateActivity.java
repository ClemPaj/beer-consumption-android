package com.clempaj.beerconsumption.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.clempaj.beerconsumption.R;

import java.util.Date;

public class SelectDateActivity extends AppCompatActivity {
    public final static String DATE = "com.example.clement.myapplication.DATE";
    private long beerId;
    private int volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);

        Intent intent = getIntent();
        beerId = SelectBeerActivity.getBeerId(intent);
        volume = SelectVolumeActivity.getVolume(intent);
    }

    public void selectNow(View view) {
        selectDate(new Date());
    }

    private void selectDate(Date date) {
        Intent intent = new Intent(this, AddConsumptionActivity.class);
        SelectBeerActivity.putExtraBeerId(intent, beerId);
        SelectVolumeActivity.putExtraVolume(intent, volume);
        putExtraDate(intent, date);
        startActivity(intent);
    }

    public static Date getDate(Intent intent) {
        return new Date(intent.getLongExtra(DATE, -1));
    }

    public static void putExtraDate(Intent intent, Date date) {
        intent.putExtra(DATE, date.getTime());
    }
}
