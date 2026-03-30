package com.akfsno.wordclockwidgets;

import android.graphics.Color;
import android.widget.RemoteViews;

public class HorizontalWordClockWidgetProvider extends BaseWordClockWidgetProvider {

    @Override
    protected int getLayoutResource() {
        return R.layout.horizontal_widget_layout;
    }

    @Override
    protected void setTexts(RemoteViews views, String hourText, String minuteText, String dayNightText, String dayOfWeekText, String dateText) {
        String timeText = hourText + " : " + minuteText.toLowerCase();
        views.setTextViewText(R.id.time_text, timeText);
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