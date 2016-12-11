package com.clempaj.beerconsumption.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.clempaj.beerconsumption.R;
import com.clempaj.beerconsumption.db.BeerContract;
import com.clempaj.beerconsumption.db.BeerDataAccess;
import com.clempaj.beerconsumption.db.ConsumptionDataAccess;

public class MainActivity extends AppCompatActivity {
    public static final String SUCCEDED = "com.example.clement.myapplication.SUCCEDED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addConsumption(View view) {
        Intent in = new Intent(this, SelectBeerActivity.class);
        startActivity(in);
    }

    public void viewConsumption(View view) {
        Intent in = new Intent(this, ViewConsumptionActivity.class);
        startActivity(in);
    }

    public static void setSucceded(Intent intent, boolean succeded) {
        intent.putExtra(SUCCEDED, succeded);
    }

    public static boolean getSucceded(Intent intent) {
        return intent.getBooleanExtra(SUCCEDED, true);
    }

    public void nels(View view) {
        Intent intent = new Intent(this, NelsActivity.class);
        startActivity(intent);
    }

    public void deleteAllBeers(View view) {
        BeerDataAccess beerAccess = new BeerDataAccess(getApplicationContext());
        beerAccess.deleteBeers();
    }

    public void deleteAllConsumptions(View view) {
        ConsumptionDataAccess consumptionAccess = new ConsumptionDataAccess(getApplicationContext());
        consumptionAccess.deleteConsumptions();
    }
}
