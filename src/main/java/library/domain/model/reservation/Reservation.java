package library.domain.model.reservation;

import library.domain.model.book.Book;
import library.domain.model.member.Member;

/**
 * 貸出予約
 */
public class Reservation {
    Member member;
    Book book;
    RetentionStatus retentionStatus;

    @Deprecated
    Reservation() {
    }

    public Reservation(Member member, Book book, RetentionStatus retentionStatus) {
        this.member = member;
        this.book = book;
        this.retentionStatus = retentionStatus;
    }

    public Member member() {
        return member;
    }

    public Book book() {
        return book;
    }

    public RetentionStatus retentionStatus() {
        return retentionStatus;
    }
}
