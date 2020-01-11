package library.domain.model.collection;

import library.domain.model.book.Book;

/**
 * 蔵書
 */
public class Collection {
    Book book;
    CollectionStatus status;

    public Collection(Book book, CollectionStatus status) {
        this.book = book;
        this.status = status;
    }
}
