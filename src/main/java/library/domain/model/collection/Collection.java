package library.domain.model.collection;

import library.domain.model.book.Book;

/**
 * 蔵書
 */
public class Collection {
    Book value;

    public Collection(Book value) {
        this.value = value;
    }
}
