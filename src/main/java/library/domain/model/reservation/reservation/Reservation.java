package library.domain.model.reservation.reservation;

import library.domain.model.book.Book;
import library.domain.model.member.Member;

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

    public Reservation(Member member, Book book) {
        this(ReservationId.generate(), member, book);
    }

    public Reservation(ReservationId reservationId, Member member, Book book) {
        this.reservationId = reservationId;
        this.member = member;
        this.book = book;
    }

    public Member member() {
        return member;
    }

    public Book book() {
        return book;
    }

    public ReservationId reservationId() {
        return reservationId;
    }
}
