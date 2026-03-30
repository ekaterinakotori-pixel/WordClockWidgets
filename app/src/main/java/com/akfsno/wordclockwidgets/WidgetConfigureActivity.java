package com.akfsno.wordclockwidgets;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;

public class WidgetConfigureActivity extends Activity {

    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private Spinner colorSpinner;
    private SeekBar fontSizeSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);

        // Set the result to CANCELED. This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED);

        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            appWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }

        colorSpinner = findViewById(R.id.color_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{"Чёрный", "Белый", "Красный", "Зелёный", "Синий", "Жёлтый"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter);

        fontSizeSeekBar = findViewById(R.id.font_size_seekbar);
        fontSizeSeekBar.setProgress((int) WidgetPreferences.getFontSize(this, appWidgetId, 24f));

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveConfiguration();
            }
        });
    }

    private void saveConfiguration() {
        int color = Color.BLACK;
        switch (colorSpinner.getSelectedItemPosition()) {
            case 0: color = Color.BLACK; break;
            case 1: color = Color.WHITE; break;
            case 2: color = Color.RED; break;
            case 3: color = Color.GREEN; break;
            case 4: color = Color.BLUE; break;
            case 5: color = Color.YELLOW; break;
        }
        WidgetPreferences.saveColor(this, appWidgetId, color);
        WidgetPreferences.saveFontSize(this, appWidgetId, fontSizeSeekBar.getProgress());

        // Make sure we pass back the original appWidgetId
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();
    }
}