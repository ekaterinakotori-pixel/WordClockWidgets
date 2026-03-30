package com.akfsno.wordclockwidgets;

import java.util.HashMap;
import java.util.Map;

public class NumberToWords {

    private static final String[] units = {
        "", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"
    };

    private static final String[] teens = {
        "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать",
        "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"
    };

    private static final String[] tens = {
        "", "", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"
    };

    private static final String[] hours = {
        "двенадцать", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять", "одиннадцать"
    };

    public static String convertHour(int hour) {
        if (hour == 0) return "двенадцать";
        if (hour > 12) hour -= 12;
        return hours[hour];
    }

    public static String convertMinute(int minute) {
        if (minute == 0) return "ноль";
        return convert(minute);
    }

    private static String convert(int number) {
        if (number < 10) {
            return units[number];
        } else if (number < 20) {
            return teens[number - 10];
        } else {
            int ten = number / 10;
            int unit = number % 10;
            return tens[ten] + (unit > 0 ? " " + units[unit] : "");
        }
    }

    public static String getDayNight(int hour) {
        return (hour >= 6 && hour < 18) ? "дня" : "ночи";
    }

    private static final String[] daysOfWeek = {
        "Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"
    };

    public static String getDayOfWeek(int dayOfWeek) {
        return daysOfWeek[dayOfWeek];
    }

    private static final String[] months = {
        "января", "февраля", "марта", "апреля", "мая", "июня",
        "июля", "августа", "сентября", "октября", "ноября", "декабря"
    };

    private static final String[] thousands = {
        "", "тысяча", "две тысячи", "три тысячи", "четыре тысячи", "пять тысяч", "шесть тысяч",
        "семь тысяч", "восемь тысяч", "девять тысяч"
    };

    public static String convertDate(int day, int month, int year) {
        String dayStr = convert(day);
        String monthStr = months[month - 1];
        String yearStr = convertYear(year);
        return dayStr + " " + monthStr + " " + yearStr;
    }

    private static String convertYear(int year) {
        if (year < 1000) return convert(year);
        int thousand = year / 1000;
        int rest = year % 1000;
        String thousandStr = thousands[thousand];
        String restStr = rest > 0 ? " " + convert(rest) : "";
        return thousandStr + restStr;
    }
}