package library.domain.model.bookcollection;

import library.domain.model.book.Book;

/**
 * 蔵書
 */
public class BookCollection {
    Book book;
    BookCollectionStatus status;

    public BookCollection(Book book, BookCollectionStatus status) {
        this.book = book;
        this.status = status;
    }
}
