package com.clempaj.beerconsumption.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import com.clempaj.beerconsumption.R;
import com.clempaj.beerconsumption.data.Consumption;
import com.clempaj.beerconsumption.data.ConsumptionAdapter;
import com.clempaj.beerconsumption.db.ConsumptionDataAccess;

import java.util.List;

public class ViewConsumptionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_consumption);

        ConsumptionDataAccess consumptionAccess = new ConsumptionDataAccess(getApplicationContext());
        List<Consumption> consumptions = consumptionAccess.getConsumptions();
        ConsumptionAdapter adapter = new ConsumptionAdapter(this, consumptions);
        TableLayout table = (TableLayout) findViewById(R.id.table_consumption);
        for (int i = 0; i < adapter.getCount(); i++) {
            table.addView(adapter.getView(i, null, table));
        }
    }
}
