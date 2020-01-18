package library.domain.model.retention;

import library.domain.model.bookcollection.BookCollection;
import library.domain.model.reservation.Reservation;
import library.domain.type.date.Date;

/**
 * 取置
 */
public class Retention {
    Reservation reservation;
    RetainedDate retainedDate;
    BookCollection bookCollection;

    public RetentionDeadline retentionDeadline() {
        return RetentionDeadline.deadline(retainedDate);
    }

    public boolean isExpired() {
        Date today = Date.now();
        return retainedDate.value.isBefore(today);
    }
}
