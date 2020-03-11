package library.domain.model.bookcollection;

import library.domain.model.book.Book;

/**
 * 蔵書
 */
public class BookCollection {
    BookCollectionCode bookCollectionCode;
    Book book;
    BookCollectionStatus bookCollectionStatus; // TODO: あとで消す

    @Deprecated
    BookCollection() {
    }

    public BookCollection(BookCollectionCode bookCollectionCode, Book book, BookCollectionStatus bookCollectionStatus) {
        this.bookCollectionCode = bookCollectionCode;
        this.book = book;
        this.bookCollectionStatus = bookCollectionStatus;
    }

    public BookCollectionStatus bookCollectionStatus() {
        return bookCollectionStatus;
    }

    public BookCollectionCode bookCollectionCode() {
        return bookCollectionCode;
    }

    public boolean sameBook(Book other) {
        return this.book.sameBook(other);
    }

}
