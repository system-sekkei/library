package library.domain.model.reservation.reservation;

import library.domain.model.item.bibliography.Book;
import library.domain.model.item.bibliography.BookNumber;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;

/**
 * 貸出予約
 */
public class Reservation {
    ReservationId reservationId;
    Member member;
    Book book;

    @Deprecated
    Reservation() {
    }

    private Reservation(ReservationId reservationId, Member member, Book book) {
        this.reservationId = reservationId;
        this.member = member;
        this.book = book;
    }

    public static Reservation of(Member member, Book book) {
        return new Reservation(ReservationId.generate(), member, book);
    }
    public Member member() {
        return member;
    }

    public MemberNumber memberNumber() {
        return member.number();
    }
    public BookNumber bookNumber() {
        return book.bookNumber();
    }
    public String showBook() {
        return book.show();
    }

    public ReservationId reservationId() {
        return reservationId;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", member=" + member +
                ", book=" + book +
                '}';
    }
}
