package library.domain.model.reservation.retention;

import library.domain.model.item.Item;
import library.domain.model.item.ItemNumber;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.ReservationNumber;
import library.domain.type.date.Date;

import java.time.LocalDate;

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
        LocalDate today = LocalDate.now();
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

    @Override
    public String toString() {
        return "Retained{" +
                ", reservation=" + reservation +
                ", retainedDate=" + retainedDate +
                ", item=" + item +
                '}';
    }
}
