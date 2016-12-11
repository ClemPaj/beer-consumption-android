package com.clempaj.beerconsumption.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.clempaj.beerconsumption.R;
import com.clempaj.beerconsumption.db.BeerDataAccess;
import com.clempaj.beerconsumption.db.ConsumptionDataAccess;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, NelsActivity.class);
        startActivity(intent);
    }

    public void addConsumption(View view) {
        Intent in = new Intent(this, SelectBeerActivity.class);
        startActivity(in);
    }

    public void viewConsumption(View view) {
        Intent in = new Intent(this, ViewConsumptionActivity.class);
        startActivity(in);
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
