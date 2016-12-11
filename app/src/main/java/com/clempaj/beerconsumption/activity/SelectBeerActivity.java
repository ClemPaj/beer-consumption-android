package com.clempaj.beerconsumption.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.clempaj.beerconsumption.R;
import com.clempaj.beerconsumption.data.Beer;
import com.clempaj.beerconsumption.data.BeerAdapter;
import com.clempaj.beerconsumption.db.BeerDataAccess;

import java.util.List;

public class SelectBeerActivity extends AppCompatActivity {
    public final static String BEER_ID = "com.clempaj.beerconsumption.BEER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_beer);

        BeerDataAccess beerAccess = new BeerDataAccess(getApplicationContext());
        List<Beer> beers = beerAccess.getBeers();
        ListView listView = (ListView) findViewById(R.id.beer_list);
        listView.setAdapter(new BeerAdapter(this, beers));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void selectBeer(View view) {
        Beer beer = (Beer) view.getTag();
        Intent intent = new Intent(this, SelectVolumeActivity.class);
        putExtraBeerId(intent, beer.getId());
        startActivity(intent);
    }

    public void gotoAddBeer(View view) {
        Intent intent = new Intent(this, AddBeerActivity.class);
        startActivity(intent);
    }

    public static long getBeerId(Intent intent) {
        return intent.getLongExtra(BEER_ID, -1);
    }

    public static void putExtraBeerId(Intent intent, long beerId) {
        intent.putExtra(BEER_ID, beerId);
    }
}
