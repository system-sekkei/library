package library.domain.model.reservation.reservation;

import library.domain.model.book.Book;
import library.domain.model.member.Member;

/**
 * 予約しようとしている本
 */
public class TryingToReserveBook {
    Member member;
    Book book;

    public TryingToReserveBook(Member member, Book book) {
        this.member = member;
        this.book = book;
    }

    public Member member() {
        return member;
    }

    public Book book() {
        return book;
    }
}
