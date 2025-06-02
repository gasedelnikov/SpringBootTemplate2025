package ru.gri.core.impl.service.date.impl;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import ru.gri.core.impl.service.date.DateService;

import java.util.TimeZone;


public class JodaDateService implements DateService {

    private final DateTimeZone timeZone;

    /**
     * Force system-wide timezone to ensure consistent
     * dates over all servers, independently of the region  the server is running.
     */
    public JodaDateService(final DateTimeZone timeZone) {
        super();
        this.timeZone = timeZone;

        System.setProperty("user.timezone", timeZone.getID());
        TimeZone.setDefault(timeZone.toTimeZone());
        DateTimeZone.setDefault(timeZone);
    }

    @Override
    public DateTime now() {
        return DateTime.now(timeZone);
    }
}
