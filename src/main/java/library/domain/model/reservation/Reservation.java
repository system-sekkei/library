package library.domain.model.reservation;

import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.retention.Retention;

/**
 * *貸出予約
 */
public class Reservation {
    ReservationNumber reservationNumber;
    Member member;
    Entry entry;

    @Deprecated
    Reservation() {
    }

    public Reservation(ReservationNumber reservationNumber, Member member, Entry entry) {
        this.reservationNumber = reservationNumber;
        this.member = member;
        this.entry = entry;
    }

    public Retention toRetention(ItemNumber itemNumber) {
        return new Retention(reservationNumber, itemNumber);
    }

    public Entry entry() {
        return entry;
    }

    public Member member() {
        return member;
    }

    public MemberNumber memberNumber() {
        return member.number();
    }
    public EntryNumber entryNumber() {
        return entry.entryNumber();
    }
    public String showMaterial() {
        return entry.show();
    }

    public ReservationNumber reservationNumber() {
        return reservationNumber;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationNumber=" + reservationNumber +
                ", member=" + member +
                ", entry=" + entry +
                '}';
    }
}
