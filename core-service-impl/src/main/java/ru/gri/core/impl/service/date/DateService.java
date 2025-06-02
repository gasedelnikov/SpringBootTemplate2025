package ru.gri.core.impl.service.date;

import org.joda.time.DateTime;


public interface DateService {

    static Long getMilliSecondsTimestamp(Long timestamp) {
        int length = (int) Math.log10(timestamp) + 1;
        if (length < 13) {
            return timestamp * 1000;
        } else {
            return timestamp;
        }
    }

    DateTime now();

}
