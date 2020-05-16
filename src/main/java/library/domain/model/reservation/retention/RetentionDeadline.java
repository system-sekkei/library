package library.domain.model.reservation.retention;

import library.domain.type.date.Date;
import library.domain.type.date.Days;

/**
 * 取置期限
 */
public class RetentionDeadline {
    Date value;

    public RetentionDeadline(Date value) {
        this.value = value;
    }

    public static RetentionDeadline deadline(RetainedDate date) {
        return new RetentionDeadline(date.value().plusDays(new Days(7)));
    }
}
