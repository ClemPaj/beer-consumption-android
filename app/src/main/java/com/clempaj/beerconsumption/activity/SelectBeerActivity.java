package com.clempaj.beerconsumption.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.clempaj.beerconsumption.R;
import com.clempaj.beerconsumption.data.Beer;
import com.clempaj.beerconsumption.data.BeerAdapter;
import com.clempaj.beerconsumption.db.BeerContract;
import com.clempaj.beerconsumption.db.BeerDbHelper;

import java.util.ArrayList;
import java.util.List;

public class SelectBeerActivity extends AppCompatActivity {
    public final static String BEER_ID = "com.example.clement.myapplication.BEER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_beer);

        List<Beer> beers = getBeers();
        ListView listView = (ListView) findViewById(R.id.beer_list);
        listView.setAdapter(new BeerAdapter(this, beers));
    }

    private List<Beer> getBeers() {
        List<Beer> beers = new ArrayList<>();
        BeerDbHelper helper = new BeerDbHelper(getApplicationContext());
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] projection = {
                BeerContract.BeerEntry._ID,
                BeerContract.BeerEntry.COLUMN_NAME_NAME,
                BeerContract.BeerEntry.COLUMN_NAME_ALCOHOL
        };

        String sortOrder = BeerContract.BeerEntry.COLUMN_NAME_NAME + " ASC";

        Cursor cursor = db.query(
                BeerContract.BeerEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(BeerContract.BeerEntry._ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(BeerContract.BeerEntry.COLUMN_NAME_NAME));
            double alcohol = cursor.getDouble(cursor.getColumnIndexOrThrow(BeerContract.BeerEntry.COLUMN_NAME_ALCOHOL));
            Beer beer = new Beer(id, name, alcohol);
            beers.add(beer);
            cursor.moveToNext();
        }
        return beers;
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
