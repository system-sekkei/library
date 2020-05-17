package library.domain.model.reservation.reservation;

import library.domain.model.item.bibliography.Book;
import library.domain.model.item.bibliography.BookNumber;

/**
 * 予約図書
 */
public class ReservedBook {
    Book book;

    public ReservedBook(Book book) {
        this.book = book;
    }

    @Deprecated
    ReservedBook() {
    }

    public Book book() {
        return book;
    }

    public boolean isA(BookNumber bookNumber) {
        return book.bookNumber().sameValue(bookNumber);
    }

    @Override
    public String toString() {
        return "ReservedBook{" +
                "book=" + book +
                '}';
    }
}
