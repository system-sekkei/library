package library.domain.model.reservation.unprepared;

import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.material.instock.EntryInStock;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.Reservation;
import library.domain.model.reservation.ReservationNumber;
import library.domain.model.reservation.wait.WaitingOrder;
import library.domain.model.retention.availability.RetentionAvailability;

/**
 * *未準備の貸出予約
 */
public class UnpreparedReservation {
    ReservationNumber reservationNumber;
    Member member;
    EntryInStock entryInStock;
    WaitingOrder 待ち順番;

    @Deprecated
    UnpreparedReservation() {
    }

    public UnpreparedReservation(ReservationNumber reservationNumber, Member member, EntryInStock entryInStock, WaitingOrder 待ち順番) {
        this.reservationNumber = reservationNumber;
        this.member = member;
        this.entryInStock = entryInStock;
        this.待ち順番 = 待ち順番;
    }

    public Entry entry() {
        return entryInStock.entry();
    }

    public Member member() {
        return member;
    }

    public MemberNumber memberNumber() {
        return member.number();
    }
    public EntryNumber entryNumber() {
        return entry().entryNumber();
    }
    public String showMaterial() {
        return entry().show();
    }

    public ReservationNumber number() {
        return reservationNumber;
    }

    public RetentionAvailability 取置可否() {
        int 自身より前の予約人数 = 待ち順番.value() - 1;
        if(entryInStock.在庫数().引く(自身より前の予約人数).value() > 0) {
            return RetentionAvailability.取置可能;
        }
        return RetentionAvailability.取置不可;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationNumber=" + reservationNumber +
                ", member=" + member +
                ", entry=" + entry() +
                '}';
    }

    // FIXME: テスト用
    public Reservation reservation() {
        return new Reservation(reservationNumber, member, entry());
    }
}
