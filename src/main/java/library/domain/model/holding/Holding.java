package library.domain.model.holding;

import library.domain.model.book.Book;

/**
 * 蔵書
 */
public class Holding {
    HoldingCode holdingCode;
    Book book;

    @Deprecated
    Holding() {
    }

    public Holding(HoldingCode holdingCode, Book book) {
        this.holdingCode = holdingCode;
        this.book = book;
    }

    public HoldingCode holdingCode() {
        return holdingCode;
    }

    // TODO: 予約本を探すメソッドにしたい (ReservedBookを受けるようにしたい)
    public boolean sameBook(Book other) {
        return this.book.sameBook(other);
    }

}
