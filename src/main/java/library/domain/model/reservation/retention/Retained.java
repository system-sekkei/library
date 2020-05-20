package library.domain.model.reservation.retention;

import library.domain.model.item.Item;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.type.date.Date;

/**
 * 取置
 */
public class Retained {
    Reservation reservation;
    RetainedDate retainedDate;
    Item item;

    public RetentionDeadline retentionDeadline() {
        return RetentionDeadline.deadline(retainedDate);
    }

    public boolean isExpired() {
        Date today = Date.now();
        return retainedDate.value.isBefore(today);
    }

    public boolean isA(Item item) {
        return item.itemNumber().sameValue(this.item.itemNumber());
    }

    public Reservation reservedBook() {
        return reservation;
    }

    public RetainedDate retainedDate() {
        return retainedDate;
    }

    public Item holding() {
        return item;
    }
}
