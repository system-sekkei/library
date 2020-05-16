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

    public Date(LocalDate value) {
        this.value = value;
    }

    public Date plusDays(Days days) {
        return new Date(value.plusDays(days.value()));
    }

    public boolean isBefore(Date date) {
        return value.isBefore(date.value);
    }

    public static Date now() {
        return new Date(LocalDate.now());
    }

    public static Date from(String value) {
        return new Date(LocalDate.parse(value, DateTimeFormatter.ISO_DATE));
    }

    @Override
    public String toString() {
        return value.format(DateTimeFormatter.ISO_DATE);
    }
}
