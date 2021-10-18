package library.domain.model.retention.wait;

import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.material.instock.EntryInStock;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.Reservation;
import library.domain.model.reservation.ReservationNumber;
import library.domain.model.retention.wait.WaitingOrder;
import library.domain.model.retention.availability.RetentionAvailability;

/**
 * 貸出予約と待ち順番
 */
public class ReservationWithWaitingOrder {
    Reservation reservation;
    EntryInStock entryInStock;
    WaitingOrder 待ち順番;

    @Deprecated
    ReservationWithWaitingOrder() {
    }

    public ReservationWithWaitingOrder(Reservation reservation, EntryInStock entryInStock, WaitingOrder 待ち順番) {
        this.reservation = reservation;
        this.entryInStock = entryInStock;
        this.待ち順番 = 待ち順番;
    }

    public Entry entry() {
        return entryInStock.entry();
    }

    public Member member() {
        return reservation().member();
    }

    public MemberNumber memberNumber() {
        return reservation().member().number();
    }
    public EntryNumber entryNumber() {
        return entry().entryNumber();
    }
    public String showMaterial() {
        return entry().show();
    }

    public ReservationNumber reservationNumber() {
        return reservation().reservationNumber();
    }

    public RetentionAvailability retentionAvailability() {
        int 自身より前の予約人数 = 待ち順番.value() - 1;
        if(entryInStock.在庫数().引く(自身より前の予約人数).value() > 0) {
            return RetentionAvailability.取置可能;
        }
        return RetentionAvailability.取置不可;
    }

    public Reservation reservation() {
        return reservation;

    }

    @Override
    public String toString() {
        return "ReservationWithWaitingOrder{" +
                "reservation=" + reservation +
                ", entryInStock=" + entryInStock +
                ", 待ち順番=" + 待ち順番 +
                '}';
    }
}
