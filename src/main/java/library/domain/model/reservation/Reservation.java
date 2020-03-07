package library.domain.model.reservation;

import library.domain.model.book.Book;
import library.domain.model.member.Member;

/**
 * 貸出予約
 */
public class Reservation {
    Member member;
    Book book;

    @Deprecated
    Reservation() {
    }

    public Reservation(Member member, Book book) {
        this.member = member;
        this.book = book;
    }

    public Member member() {
        return member;
    }
}
