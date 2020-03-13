package library.domain.model.reservation.reservation;

import library.domain.model.book.Book;
import library.domain.model.member.Member;

/**
 * 貸出予約
 */
public class ReservedBook {
    Member member;
    Book book;

    @Deprecated
    ReservedBook() {
    }

    public ReservedBook(Member member, Book book) {
        this.member = member;
        this.book = book;
    }

    public Member member() {
        return member;
    }
}
