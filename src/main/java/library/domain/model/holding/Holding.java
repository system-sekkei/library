package library.domain.model.holding;

import library.domain.model.book.Book;

/**
 * 蔵書
 */
public class Holding {
    HoldingCode holdingCode;
    Book book;
    HoldingStatus holdingStatus; // TODO: あとで消す

    @Deprecated
    Holding() {
    }

    public Holding(HoldingCode holdingCode, Book book, HoldingStatus holdingStatus) {
        this.holdingCode = holdingCode;
        this.book = book;
        this.holdingStatus = holdingStatus;
    }

    public HoldingStatus holdingStatus() {
        return holdingStatus;
    }

    public HoldingCode holdingCode() {
        return holdingCode;
    }

    public boolean sameBook(Book other) {
        return this.book.sameBook(other);
    }

}
