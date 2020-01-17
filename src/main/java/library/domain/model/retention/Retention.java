package library.domain.model.retention;

import library.domain.model.bookcollection.BookCollection;
import library.domain.model.reservation.Reservation;

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
}
