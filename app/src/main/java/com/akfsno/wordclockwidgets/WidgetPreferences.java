package com.akfsno.wordclockwidgets;

import android.content.Context;
import android.content.SharedPreferences;

public class WidgetPreferences {

    private static final String PREFS_NAME = "WidgetPrefs";

    public static void saveColor(Context context, int appWidgetId, int color) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putInt("color_" + appWidgetId, color).apply();
    }

    public static int getColor(Context context, int appWidgetId, int defaultColor) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getInt("color_" + appWidgetId, defaultColor);
    }

    public static void saveFontSize(Context context, int appWidgetId, float fontSize) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putFloat("fontSize_" + appWidgetId, fontSize).apply();
    }

    public static float getFontSize(Context context, int appWidgetId, float defaultSize) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getFloat("fontSize_" + appWidgetId, defaultSize);
    }

    // Add more for other settings
}