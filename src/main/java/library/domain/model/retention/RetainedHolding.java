package library.domain.model.retention;

import library.domain.model.holding.Holding;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.type.date.Date;

/**
 * 取置済み蔵書
 */
public class RetainedHolding {
    Reservation reservation;
    RetainedDate retainedDate;
    Holding holding;

    public RetentionDeadline retentionDeadline() {
        return RetentionDeadline.deadline(retainedDate);
    }

    public boolean isExpired() {
        Date today = Date.now();
        return retainedDate.value.isBefore(today);
    }

    public boolean isA(Holding holding) {
        return holding.holdingCode().sameValue(this.holding.holdingCode());
    }

    public Reservation reservedBook() {
        return reservation;
    }

    public RetainedDate retainedDate() {
        return retainedDate;
    }

    public Holding holding() {
        return holding;
    }
}
