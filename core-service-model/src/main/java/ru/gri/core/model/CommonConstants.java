package ru.gri.core.model;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;

public interface CommonConstants {

//    Locale LOCALE_RU = getRuLocale();
//    ZoneId TIMEZONE_UTC3 = ZoneId.of("UTC+3");

    String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
//    String DATETIME_WITH_TIMEZONE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
//    String DATE_FORMAT = "yyyy-MM-dd";
//    String YEAR_FORMAT = "yyyy";
//    String MONTH_FORMAT = "MMMM";
//    String DAY_FORMAT = "dd";
//    String TIME_FORMAT = "HH:mm";

    SimpleDateFormat DATETIME_FORMATTER = new SimpleDateFormat(DATETIME_FORMAT);
//    DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT, LOCALE_RU);
//    DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
//    DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT).withZone(TIMEZONE_UTC3);
//    DateTimeFormatter DATETIME_FORMATTER_WITH_ZONE = FORMATTER.withZone(ZoneId.systemDefault());

    static Locale getRuLocale() {
//    String[] MONTH_NAMES = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        String[] months = {"января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря"};

        Locale locale = new Locale("ru");
        DateFormatSymbols dfs = DateFormatSymbols.getInstance(locale);
        dfs.setMonths(months);

        return locale;
    }
}
