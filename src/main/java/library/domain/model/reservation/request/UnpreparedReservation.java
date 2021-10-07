package library.domain.model.reservation.request;

import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.material.instock.EntryInStock;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;

/**
 * *未準備の貸出予約
 */
public class UnpreparedReservation {
    ReservationNumber reservationNumber;
    Member member;
    EntryInStock entryInStock;

    @Deprecated
    UnpreparedReservation() {
    }

    public UnpreparedReservation(ReservationNumber reservationNumber, Member member, EntryInStock entryInStock) {
        this.reservationNumber = reservationNumber;
        this.member = member;
        this.entryInStock = entryInStock;
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
