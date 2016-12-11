package com.clempaj.beerconsumption.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.clempaj.beerconsumption.R;

public class SelectVolumeActivity extends AppCompatActivity {
    public final static String VOLUME = "com.clempaj.beerconsumption.VOLUME";

    private long beerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_volume);

        Intent intent = getIntent();
        beerId = SelectBeerActivity.getBeerId(intent);

        ((TextView) findViewById(R.id.volume_manual)).setOnEditorActionListener(new OnEditorDoneSelectVolume());
    }

    public void selectButtonVolume(View view) {
        clearTextVolume();
        switch (view.getId()) {
            case R.id.cl25 : selectVolume(25);
                break;
            case R.id.cl33 : selectVolume(33);
                break;
            case R.id.cl50 : selectVolume(50);
                break;
        }
    }

    private class OnEditorDoneSelectVolume implements TextView.OnEditorActionListener {
        @Override
        public boolean onEditorAction(TextView textView, int action, KeyEvent keyEvent) {
            if (action == EditorInfo.IME_ACTION_DONE) {
                selectTextVolume(textView);
                return true;
            }
            return false;
        }
    }

    public void selectTextVolume(View view) {
        EditText editText = (EditText) findViewById(R.id.volume_manual);
        selectVolume(Integer.parseInt(editText.getText().toString()));
    }

    private void selectVolume(int volume) {
        Intent intent = new Intent(this, SelectDateActivity.class);
        putExtraVolume(intent, volume);
        SelectBeerActivity.putExtraBeerId(intent, beerId);
        startActivity(intent);
    }

    private void clearTextVolume() {
        EditText volumeText = (EditText) findViewById(R.id.volume_manual);
        volumeText.setText("");
    }

    public static int getVolume(Intent intent) {
        return intent.getIntExtra(VOLUME, -1);
    }

    public static void putExtraVolume(Intent intent, int volume) {
        intent.putExtra(VOLUME, volume);
    }
}
