package com.akfsno.wordclockwidgets;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView title = findViewById(R.id.title_text);
        title.setText("Word Clock Widgets");

        Button addWidgetButton = findViewById(R.id.add_widget_button);
        addWidgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(MainActivity.this);
                int[] widgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(MainActivity.this, WordClockWidgetProvider.class));
                if (widgetIds.length == 0) {
                    widgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(MainActivity.this, HorizontalWordClockWidgetProvider.class));
                }
                if (widgetIds.length == 0) {
                    widgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(MainActivity.this, ExtendedWordClockWidgetProvider.class));
                }
                if (widgetIds.length == 0) {
                    widgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(MainActivity.this, AcidWordClockWidgetProvider.class));
                }
                if (widgetIds.length == 0) {
                    widgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(MainActivity.this, NeonWordClockWidgetProvider.class));
                }

                if (widgetIds.length > 0) {
                    Intent intent = new Intent(MainActivity.this, WidgetConfigureActivity.class);
                    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetIds[0]);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Сначала добавьте виджет на главный экран", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button updateWidgetsButton = findViewById(R.id.update_widgets_button);
        updateWidgetsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update all widgets
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(MainActivity.this);
                ComponentName[] providers = {
                    new ComponentName(MainActivity.this, WordClockWidgetProvider.class),
                    new ComponentName(MainActivity.this, HorizontalWordClockWidgetProvider.class),
                    new ComponentName(MainActivity.this, ExtendedWordClockWidgetProvider.class),
                    new ComponentName(MainActivity.this, AcidWordClockWidgetProvider.class),
                    new ComponentName(MainActivity.this, NeonWordClockWidgetProvider.class)
                };
                for (ComponentName provider : providers) {
                    int[] appWidgetIds = appWidgetManager.getAppWidgetIds(provider);
                    if (appWidgetIds.length > 0) {
                        Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                        intent.setComponent(provider);
                        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
                        sendBroadcast(intent);
                    }
                }
            }
        });
    }
}