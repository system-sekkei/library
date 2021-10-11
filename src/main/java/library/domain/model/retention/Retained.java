package library.domain.model.retention;

import library.domain.model.material.item.ItemNumber;
import library.domain.model.loan.LoanDate;
import library.domain.model.loan.LoanRequest;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.Reservation;
import library.domain.model.reservation.ReservationNumber;

/**
 * 取置資料
 */
public class Retained {
    Reservation reservation;
    RetainedDate retainedDate;
    ItemNumber itemNumber;

    public boolean isExpired() {
        ExpireDate expireDate = ExpireDate.of(retainedDate);
        return expireDate.isExpired();
    }

    public ReservationNumber reservationNumber() {
        return reservation.reservationNumber();
    }
    public String showExpireDate() {
        return ExpireDate.of(retainedDate).show();
    }

    public String showMaterial() {
        return reservation.showMaterial();
    }

    public ItemNumber itemNumber() {
        return itemNumber;
    }

    public MemberNumber memberNumber() {
        return reservation.memberNumber();
    }

    public LoanRequest toLoanRequest(LoanDate loanDate) {
        return new LoanRequest(reservation.memberNumber(), itemNumber, loanDate);
    }

    public RetainedDate retainedDate() {
        return retainedDate;
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
