package library.domain.type.date;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * 日付
 */
public class Date {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate value;

    @Deprecated
    public Date() {
    }

    public static Date from(String value) {
        return new Date(LocalDate.parse(value, DateTimeFormatter.ISO_DATE));
    }

    public Date(LocalDate value) {
        this.value = value;
    }

    public LocalDate value() {
        return value;
    }

    @Override
    public String toString() {
        return value.format(DateTimeFormatter.ISO_DATE);
    }

    public Date plusDays(int days) {
        return new Date(value.plusDays(days));
    }

    public Date plus(Period period) {
        return new Date(value.plus(period));
    }

    public boolean isBefore(Date date) {
        return value.isBefore(date.value);
    }

    public static Date now() {
        return new Date(LocalDate.now());
    }
}
