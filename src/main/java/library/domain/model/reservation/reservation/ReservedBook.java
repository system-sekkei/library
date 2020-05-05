package library.domain.model.reservation.reservation;

import library.domain.model.book.bibliography.Book;
import library.domain.model.book.bibliography.BookId;

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

    public boolean isA(BookId bookId) {
        return book.bookId().sameValue(bookId);
    }
}
