package library.domain.model.reservation.retention;

import library.domain.model.item.ItemNumber;
import library.domain.model.loan.loan.LoanDate;
import library.domain.model.loan.loan.LoanRequest;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.reservation.Reservation;

import java.time.LocalDate;

/**
 * 取置済
 */
public class Retained {
    Reservation reservation;
    RetainedDate retainedDate;
    ItemNumber itemNumber;

    public RetentionDeadline retentionDeadline() {
        return RetentionDeadline.deadline(retainedDate);
    }

    public boolean isExpired() {
        LocalDate today = LocalDate.now();
        return retainedDate.value.isBefore(today);
    }

    public String showBook() {
        return reservation.showBook();
    }

    public MemberNumber memberNumber() {
        return reservation.memberNumber();
    }

    public RetainedDate retainedDate() {
        return retainedDate;
    }

    public ItemNumber itemNumber() {
        return itemNumber;
    }

    public LoanRequest toLoanRequest() {
        return new LoanRequest(reservation.memberNumber(), itemNumber, LoanDate.from(retainedDate.value));
    }
    @Override
    public String toString() {
        return "Retained{" +
                ", reservation=" + reservation +
                ", retainedDate=" + retainedDate +
                ", itemNumber=" + itemNumber +
                '}';
    }
}
