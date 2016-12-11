package com.clempaj.beerconsumption.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.clempaj.beerconsumption.R;
import com.clempaj.beerconsumption.db.BeerDataAccess;

public class AddBeerActivity extends AppCompatActivity {
    private BeerDataAccess beerAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beer);

        beerAccess = new BeerDataAccess(getApplicationContext());
        findViewById(R.id.add_beer_name).setOnFocusChangeListener(new OnChangeFocusClearWarning());
        findViewById(R.id.add_beer_alcohol).setOnFocusChangeListener(new OnChangeFocusClearWarning());
        clearWarningText();
    }

    private class OnChangeFocusClearWarning implements View.OnFocusChangeListener {
        @Override
        public void onFocusChange(View view, boolean b) {
            clearWarningText();
        }
    }

    public void addBeer(View view) {
        clearWarningText();
        String name = getName();
        if (name == null) return;
        Double alcohol = getAlcohol();
        if (alcohol == null) return;
        if (!beerAccess.addBeer(name, alcohol.doubleValue())) {
            setWarningText(getString(R.string.add_beer_warning_db_failed));
            return;
        }

        Intent intent = new Intent(this, SelectBeerActivity.class); //TODO: revenir au parent
        startActivity(intent);
    }

    private String getName() {
        String name = ((EditText) findViewById(R.id.add_beer_name)).getText().toString();
        if (name == null) {
            setWarningText(getString(R.string.add_beer_warning_no_name));
            return null;
        }
        name = name.trim();
        if (name.length() == 0) {
            setWarningText(getString(R.string.add_beer_warning_empty_name));
            return null;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private Double getAlcohol() {
        try {
            return Double.parseDouble(((EditText) findViewById(R.id.add_beer_alcohol)).getText().toString());
        } catch (NumberFormatException e) {
            setWarningText(getString(R.string.add_beer_warning_bad_alcohol));
        } catch (NullPointerException e) {
            setWarningText(getString(R.string.add_beer_warning_no_alcohol));
        }
        return null;
    }

    private void setWarningText(String warningText) {
        getWarning().setText(warningText);
    }

    private void clearWarningText() {
        getWarning().setText("");
    }

    private TextView getWarning() {
        return (TextView) findViewById(R.id.warning_add_beer);
    }
}
