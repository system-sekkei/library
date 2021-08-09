package library.domain.model.reservation.request;

import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;

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

    private Reservation(ReservationNumber reservationNumber, Member member, Entry entry) {
        this.reservationNumber = reservationNumber;
        this.member = member;
        this.entry = entry;
    }

    public static Reservation of(Member member, Entry entry) {
        return new Reservation(ReservationNumber.generate(), member, entry);
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

    public ReservationNumber number() {
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
