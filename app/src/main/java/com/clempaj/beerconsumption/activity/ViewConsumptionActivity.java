package com.clempaj.beerconsumption.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import com.clempaj.beerconsumption.R;
import com.clempaj.beerconsumption.data.Beer;
import com.clempaj.beerconsumption.data.Consumption;
import com.clempaj.beerconsumption.data.ConsumptionAdapter;
import com.clempaj.beerconsumption.db.BeerContract;
import com.clempaj.beerconsumption.db.ConsumptionDbHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewConsumptionActivity extends AppCompatActivity {

    private static final String QUERY_BEER_NAME_AND_CONSUMPION =
            "SELECT * FROM " + BeerContract.BeerEntry.TABLE_NAME + " INNER JOIN " + BeerContract.ConsumptionEntry.TABLE_NAME
                    + " ON " + BeerContract.BeerEntry.TABLE_COLUMN_ID + " = " + BeerContract.ConsumptionEntry.TABLE_COLUMN_BEER_ID
                    + " ORDER BY " + BeerContract.ConsumptionEntry.TABLE_COLUMN_DATE + " DESC";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_consumption);

        List<Consumption> consumptions = getConsumptions();
        ConsumptionAdapter adapter = new ConsumptionAdapter(this, consumptions);
        TableLayout table = (TableLayout) findViewById(R.id.table_consumption);
        for (int i = 0; i < adapter.getCount(); i++) {
            table.addView(adapter.getView(i, null, table));
        }
    }

    private List<Consumption> getConsumptions() {
        List<Consumption> consumptions = new ArrayList<>();
        ConsumptionDbHelper helper = new ConsumptionDbHelper(getApplicationContext());
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery(QUERY_BEER_NAME_AND_CONSUMPION, new String[]{});

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            long beerId = cursor.getLong(cursor.getColumnIndexOrThrow(BeerContract.BeerEntry._ID));
            String beerName = cursor.getString(cursor.getColumnIndexOrThrow(BeerContract.BeerEntry.COLUMN_NAME_NAME));
            double alcohol = cursor.getDouble(cursor.getColumnIndexOrThrow(BeerContract.BeerEntry.COLUMN_NAME_ALCOHOL));
            int volume = cursor.getInt(cursor.getColumnIndexOrThrow(BeerContract.ConsumptionEntry.COLUMN_NAME_VOLUME));
            long dateLong = cursor.getLong(cursor.getColumnIndexOrThrow(BeerContract.ConsumptionEntry.COLUMN_NAME_DATE));

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
