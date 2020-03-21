package library.domain.model.reservation.reservation;

import library.domain.model.book.Book;
import library.domain.model.member.Member;

/**
 * 貸出予約
 */
 // TODO: パッケージはReservation(=貸出予約)にしている。こっちは貸出予約図書？？
public class ReservedBook {
    ReservationId reservationId;
    Member member;
    Book book;

    @Deprecated
    ReservedBook() {
    }

    public ReservedBook(ReservationId reservationId, Member member, Book book) {
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
