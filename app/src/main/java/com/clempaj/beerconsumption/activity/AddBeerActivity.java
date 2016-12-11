package com.clempaj.beerconsumption.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.clempaj.beerconsumption.R;
import com.clempaj.beerconsumption.db.BeerContract;
import com.clempaj.beerconsumption.db.BeerDbHelper;

public class AddBeerActivity extends AppCompatActivity {
    private BeerDbHelper beerDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beer);

        beerDb = new BeerDbHelper(getApplicationContext());
    }

    public void addBeer(View view) {
        Double alcohol = getAlcohol();
        String name = getName();
        if (!insertBeerDb(name, alcohol)) return;
        Intent intent = new Intent(this, SelectBeerActivity.class); //TODO: revenir au parent
        startActivity(intent);
    }

    private String getName() {
        String name = ((EditText) findViewById(R.id.add_beer_name)).getText().toString().trim();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private Double getAlcohol() {
        try {
            return Double.parseDouble(((EditText) findViewById(R.id.add_beer_alcohol)).getText().toString());
        } catch (NumberFormatException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    private boolean insertBeerDb(String name, Double alcohol) {
        if (alcohol == null) return false;
        SQLiteDatabase db = beerDb.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(BeerContract.BeerEntry.COLUMN_NAME_NAME, name);
        content.put(BeerContract.BeerEntry.COLUMN_NAME_ALCOHOL, alcohol);
        long id = db.insert(BeerContract.BeerEntry.TABLE_NAME, null, content);
        return id != -1;
    }
}
