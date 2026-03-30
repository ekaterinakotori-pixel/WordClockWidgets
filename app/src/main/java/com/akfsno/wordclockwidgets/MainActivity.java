package com.akfsno.wordclockwidgets;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
                // Open widget picker - actually, better to show instructions
                Intent intent = new Intent(MainActivity.this, WidgetConfigureActivity.class);
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
                startActivity(intent);
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
                    for (int appWidgetId : appWidgetIds) {
                        // Trigger update
                        Intent intent = new Intent(MainActivity.this, provider.getClassName());
                        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, new int[]{appWidgetId});
                        sendBroadcast(intent);
                    }
                }
            }
        });
    }
}