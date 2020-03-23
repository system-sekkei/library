package library.domain.model.counter;

import library.domain.model.book.BookId;
import library.domain.model.holding.Holdings;

/**
 * 目録
 */
public class Catalog {
    BookId bookId;
    Holdings holdings;

    public Catalog(BookId bookId, Holdings holdings) {
        this.bookId = bookId;
        this.holdings = holdings;
    }

    public boolean sameBook(BookId bookId) {
        return this.bookId.sameValue(bookId);
    }
}
