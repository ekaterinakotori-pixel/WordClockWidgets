package com.akfsno.wordclockwidgets;

import android.graphics.Color;
import android.widget.RemoteViews;

public class WordClockWidgetProvider extends BaseWordClockWidgetProvider {

    @Override
    protected int getLayoutResource() {
        return R.layout.widget_layout;
    }

    @Override
    protected void setTexts(RemoteViews views, String hourText, String minuteText, String dayNightText, String dayOfWeekText, String dateText) {
        views.setTextViewText(R.id.hour_text, hourText);
        views.setTextViewText(R.id.day_night_text, dayNightText);
        views.setTextViewText(R.id.minute_text, minuteText);
    }

    @Override
    protected int getDefaultTextColor() {
        return Color.BLACK;
    }

    @Override
    protected int getDefaultBorderColor() {
        return Color.RED;
    }
}