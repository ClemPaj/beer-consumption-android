package com.clempaj.beerconsumption.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
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
        TextView alcoholView = (TextView) findViewById(R.id.add_beer_alcohol);
        alcoholView.setOnEditorActionListener(new OnEditorDoneAddBeer());
        clearWarningText();

        View nameView = findViewById(R.id.add_beer_name);
        if(nameView.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }

    private class OnEditorDoneAddBeer implements TextView.OnEditorActionListener {
        @Override
        public boolean onEditorAction(TextView textView, int action, KeyEvent keyEvent) {
            if (action == EditorInfo.IME_ACTION_DONE) {
                addBeer(textView);
                return true;
            }
            return false;
        }
    }

    public void addBeer(View view) {
        clearWarningText();
        String name = getName();
        if (name == null) return;
        Double alcohol = getAlcohol();
        if (alcohol == null) return;
        if (!beerAccess.addBeer(name, alcohol)) {
            View nameView = findViewById(R.id.add_beer_name);
            setWarningTextAndFocus(nameView, getString(R.string.add_beer_warning_db_failed));
            return;
        }

        Intent intent = new Intent(this, SelectBeerActivity.class);
        startActivity(intent);
    }

    private String getName() {
        EditText nameView = (EditText) findViewById(R.id.add_beer_name);
        String name = nameView.getText().toString();
        name = name.trim();
        if (name.length() == 0) {
            setWarningTextAndFocus(nameView, getString(R.string.add_beer_warning_empty_name));
            return null;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private Double getAlcohol() {
        EditText alcoholView = (EditText) findViewById(R.id.add_beer_alcohol);
        try {
            return Double.parseDouble(alcoholView.getText().toString());
        } catch (NumberFormatException e) {
            setWarningTextAndFocus(alcoholView, getString(R.string.add_beer_warning_bad_alcohol));
        } catch (NullPointerException e) {
            setWarningTextAndFocus(alcoholView, getString(R.string.add_beer_warning_no_alcohol));
        }
        return null;
    }

    private void setWarningTextAndFocus(View view, String warningText) {
        getWarning().setText(warningText);
        view.requestFocus();
    }

    private void clearWarningText() {
        getWarning().setText("");
    }

    private TextView getWarning() {
        return (TextView) findViewById(R.id.warning_add_beer);
    }
}
