package com.clempaj.beerconsumption.data;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.clempaj.beerconsumption.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class ConsumptionAdapter extends BaseAdapter {
    private Context context;
    private List<Consumption> consumptions;

    public ConsumptionAdapter(Context context, List<Consumption> consumptions) {
        this.consumptions = consumptions;
        this.context = context;
    }

    @Override
    public int getCount() {
        return consumptions.size();
    }

    @Override
    public Object getItem(int i) {
        return consumptions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(R.layout.consumption, viewGroup, false);

        Consumption consumption = consumptions.get(i);
        TextView date = (TextView) row.findViewById(R.id.consumption_date);
        date.setText(new SimpleDateFormat("dd/MM HH:mm").format(consumption.getDate()));

        TextView beer = (TextView) row.findViewById(R.id.consumption_beer_name);
        beer.setText(consumption.getBeer().getName());

        TextView volume = (TextView) row.findViewById(R.id.consumption_volume);
        volume.setText(consumption.getVolume() + " cl");

        return row;
    }
}