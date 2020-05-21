package library.domain.model.reservation.retention;

import library.domain.type.date.Date;
import library.domain.type.date.Days;

import java.time.LocalDate;

/**
 * 取置期限
 */
public class RetentionDeadline {
    LocalDate value;

    public RetentionDeadline(LocalDate value) {
        this.value = value;
    }

    public static RetentionDeadline deadline(RetainedDate date) {
        return new RetentionDeadline(date.value.plusDays(7));
    }
}
