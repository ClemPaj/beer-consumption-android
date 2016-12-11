package com.clempaj.beerconsumption.data;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.clempaj.beerconsumption.R;

import java.util.List;

public class BeerAdapter extends BaseAdapter {
    private List<Beer> beers;
    private Context context;

    public BeerAdapter(Context context, List<Beer> beers) {
        this.context = context;
        this.beers = beers;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(R.layout.beer, viewGroup, false);

        Beer beer = beers.get(i);
        Button button = (Button) row.findViewById(R.id.button_beer);
        button.setText(beer.getName());
        button.setTag(beer);

        return row;
    }

    @Override
    public int getCount() {
        return beers.size();
    }

    @Override
    public Object getItem(int i) {
        return beers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return beers.get(i).getId();
    }
}

