package library.domain.model.reservation.reservation;

import library.domain.model.book.Book;
import library.domain.model.member.Member;

/**
 * 貸出予約
 */
public class Reservation {
    ReservationId reservationId;
    Member member;
    ReservedBook reservedBook;

    @Deprecated
    Reservation() {
    }

    public Reservation(Member member, ReservedBook reservedBook) {
        this(ReservationId.generate(), member, reservedBook);
    }

    public Reservation(ReservationId reservationId, Member member, ReservedBook reservedBook) {
        this.reservationId = reservationId;
        this.member = member;
        this.reservedBook = reservedBook;
    }

    public Member member() {
        return member;
    }

    public Book book() {
        return reservedBook.book();
    }

    public ReservationId reservationId() {
        return reservationId;
    }
}
