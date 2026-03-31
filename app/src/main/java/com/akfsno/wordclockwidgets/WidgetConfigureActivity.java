package com.akfsno.wordclockwidgets;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

public class WidgetConfigureActivity extends Activity {

    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private Spinner styleSpinner, colorSpinner, borderColorSpinner, backgroundColorSpinner;
    private SeekBar fontSizeSeekBar, borderWidthSeekBar, backgroundAlphaSeekBar;
    private SeekBar hourOffsetXSeekBar, hourOffsetYSeekBar, minuteOffsetXSeekBar, minuteOffsetYSeekBar;
    private CheckBox showSecondsCheckbox, showDateCheckbox, showDayOfWeekCheckbox, use12HourCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);

        setResult(RESULT_CANCELED);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            appWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            appWidgetId = findExistingWidgetId();
            if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
                Toast.makeText(this, "Сначала добавьте виджет на главный экран", Toast.LENGTH_LONG).show();
                finish();
                return;
            }
        }

        styleSpinner = findViewById(R.id.style_spinner);
        ArrayAdapter<String> styleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{"Базовый", "Горизонтальный", "Расширенный", "Кислотный", "Неоновый"});
        styleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        styleSpinner.setAdapter(styleAdapter);

        colorSpinner = findViewById(R.id.color_spinner);
        ArrayAdapter<String> colorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{"Чёрный", "Белый", "Красный", "Зелёный", "Синий", "Жёлтый", "Оранжевый", "Фиолетовый", "Розовый", "Серый"});
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(colorAdapter);

        borderColorSpinner = findViewById(R.id.border_color_spinner);
        borderColorSpinner.setAdapter(colorAdapter);

        backgroundColorSpinner = findViewById(R.id.background_color_spinner);
        backgroundColorSpinner.setAdapter(colorAdapter);

        fontSizeSeekBar = findViewById(R.id.font_size_seekbar);
        fontSizeSeekBar.setProgress((int) WidgetPreferences.getFontSize(this, appWidgetId, 24f));

        borderWidthSeekBar = findViewById(R.id.border_width_seekbar);
        borderWidthSeekBar.setProgress(WidgetPreferences.getBorderWidth(this, appWidgetId, 2));

        backgroundAlphaSeekBar = findViewById(R.id.background_alpha_seekbar);
        backgroundAlphaSeekBar.setProgress(WidgetPreferences.getBackgroundAlpha(this, appWidgetId, 0));

        hourOffsetXSeekBar = findViewById(R.id.hour_offset_x_seekbar);
        hourOffsetYSeekBar = findViewById(R.id.hour_offset_y_seekbar);
        minuteOffsetXSeekBar = findViewById(R.id.minute_offset_x_seekbar);
        minuteOffsetYSeekBar = findViewById(R.id.minute_offset_y_seekbar);

        hourOffsetXSeekBar.setProgress(WidgetPreferences.getOffsetX(this, appWidgetId, "hour", 0));
        hourOffsetYSeekBar.setProgress(WidgetPreferences.getOffsetY(this, appWidgetId, "hour", 0));
        minuteOffsetXSeekBar.setProgress(WidgetPreferences.getOffsetX(this, appWidgetId, "minute", 0));
        minuteOffsetYSeekBar.setProgress(WidgetPreferences.getOffsetY(this, appWidgetId, "minute", 0));

        showSecondsCheckbox = findViewById(R.id.show_seconds_checkbox);
        showDateCheckbox = findViewById(R.id.show_date_checkbox);
        showDayOfWeekCheckbox = findViewById(R.id.show_day_of_week_checkbox);
        use12HourCheckbox = findViewById(R.id.use_12hour_checkbox);

        showSecondsCheckbox.setChecked(WidgetPreferences.getShowSeconds(this, appWidgetId, false));
        showDateCheckbox.setChecked(WidgetPreferences.getShowDate(this, appWidgetId, false));
        showDayOfWeekCheckbox.setChecked(WidgetPreferences.getShowDayOfWeek(this, appWidgetId, false));
        use12HourCheckbox.setChecked(WidgetPreferences.getUse12HourFormat(this, appWidgetId, false));

        String savedStyle = WidgetPreferences.getStyle(this, appWidgetId, "Базовый");
        int stylePosition = getStylePosition(savedStyle);
        if (stylePosition >= 0) styleSpinner.setSelection(stylePosition);

        int savedColor = WidgetPreferences.getColor(this, appWidgetId, Color.BLACK);
        colorSpinner.setSelection(getPositionForColor(savedColor));
        borderColorSpinner.setSelection(getPositionForColor(WidgetPreferences.getBorderColor(this, appWidgetId, Color.RED)));
        backgroundColorSpinner.setSelection(getPositionForColor(WidgetPreferences.getBackgroundColor(this, appWidgetId, Color.TRANSPARENT)));

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveConfiguration();
            }
        });
    }

    private void saveConfiguration() {
        String style = (String) styleSpinner.getSelectedItem();
        WidgetPreferences.saveStyle(this, appWidgetId, style);

        int color = getColorFromSpinner(colorSpinner);
        WidgetPreferences.saveColor(this, appWidgetId, color);

        float fontSize = fontSizeSeekBar.getProgress();
        WidgetPreferences.saveFontSize(this, appWidgetId, fontSize);

        int borderColor = getColorFromSpinner(borderColorSpinner);
        WidgetPreferences.saveBorderColor(this, appWidgetId, borderColor);

        int borderWidth = borderWidthSeekBar.getProgress();
        WidgetPreferences.saveBorderWidth(this, appWidgetId, borderWidth);

        int backgroundColor = getColorFromSpinner(backgroundColorSpinner);
        WidgetPreferences.saveBackgroundColor(this, appWidgetId, backgroundColor);

        int backgroundAlpha = backgroundAlphaSeekBar.getProgress();
        WidgetPreferences.saveBackgroundAlpha(this, appWidgetId, backgroundAlpha);

        WidgetPreferences.saveOffsetX(this, appWidgetId, "hour", hourOffsetXSeekBar.getProgress());
        WidgetPreferences.saveOffsetY(this, appWidgetId, "hour", hourOffsetYSeekBar.getProgress());
        WidgetPreferences.saveOffsetX(this, appWidgetId, "minute", minuteOffsetXSeekBar.getProgress());
        WidgetPreferences.saveOffsetY(this, appWidgetId, "minute", minuteOffsetYSeekBar.getProgress());

        WidgetPreferences.saveShowSeconds(this, appWidgetId, showSecondsCheckbox.isChecked());
        WidgetPreferences.saveShowDate(this, appWidgetId, showDateCheckbox.isChecked());
        WidgetPreferences.saveShowDayOfWeek(this, appWidgetId, showDayOfWeekCheckbox.isChecked());
        WidgetPreferences.saveUse12HourFormat(this, appWidgetId, use12HourCheckbox.isChecked());

        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        setResult(RESULT_OK, resultValue);

        // Update the widget
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        Intent intent = new Intent(this, WordClockWidgetProvider.class); // Or determine the correct provider
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, new int[]{appWidgetId});
        sendBroadcast(intent);

        finish();
    }

    private int getColorFromSpinner(Spinner spinner) {
        switch (spinner.getSelectedItemPosition()) {
            case 0: return Color.BLACK;
            case 1: return Color.WHITE;
            case 2: return Color.RED;
            case 3: return Color.GREEN;
            case 4: return Color.BLUE;
            case 5: return Color.YELLOW;
            case 6: return Color.rgb(255, 165, 0); // Orange
            case 7: return Color.rgb(128, 0, 128); // Purple
            case 8: return Color.rgb(255, 192, 203); // Pink
            case 9: return Color.GRAY;
            default: return Color.BLACK;
        }
    }

    private int getStylePosition(String style) {
        switch (style) {
            case "Базовый": return 0;
            case "Горизонтальный": return 1;
            case "Расширенный": return 2;
            case "Кислотный": return 3;
            case "Неоновый": return 4;
            default: return 0;
        }
    }

    private int getPositionForColor(int color) {
        if (color == Color.BLACK) return 0;
        if (color == Color.WHITE) return 1;
        if (color == Color.RED) return 2;
        if (color == Color.GREEN) return 3;
        if (color == Color.BLUE) return 4;
        if (color == Color.YELLOW) return 5;
        if (color == Color.rgb(255, 165, 0)) return 6;
        if (color == Color.rgb(128, 0, 128)) return 7;
        if (color == Color.rgb(255, 192, 203)) return 8;
        if (color == Color.GRAY) return 9;
        return 0;
    }

    private int findExistingWidgetId() {
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        int[] widgetIds;

        widgetIds = manager.getAppWidgetIds(new ComponentName(this, WordClockWidgetProvider.class));
        if (widgetIds.length > 0) return widgetIds[0];

        widgetIds = manager.getAppWidgetIds(new ComponentName(this, HorizontalWordClockWidgetProvider.class));
        if (widgetIds.length > 0) return widgetIds[0];

        widgetIds = manager.getAppWidgetIds(new ComponentName(this, ExtendedWordClockWidgetProvider.class));
        if (widgetIds.length > 0) return widgetIds[0];

        widgetIds = manager.getAppWidgetIds(new ComponentName(this, AcidWordClockWidgetProvider.class));
        if (widgetIds.length > 0) return widgetIds[0];

        widgetIds = manager.getAppWidgetIds(new ComponentName(this, NeonWordClockWidgetProvider.class));
        if (widgetIds.length > 0) return widgetIds[0];

        return AppWidgetManager.INVALID_APPWIDGET_ID;
    }
}