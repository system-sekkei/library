package library.domain.model.reservation.retention;

import library.domain.model.item.Item;
import library.domain.model.item.bibliography.Book;

import static library.domain.model.reservation.retention.BookMatchingType.一致;
import static library.domain.model.reservation.retention.BookMatchingType.不一致;

/**
 * 蔵書と本の照合
 */
public class BookMatching {
    Book book;
    Item item;

    public BookMatching(Book book, Item item) {
        this.book = book;
        this.item = item;
    }

    public BookMatchingType result() {
        Book bookOfItem = item.book();
        boolean result = bookOfItem.isSame(book);
        return result ? 一致 : 不一致;
    }

    public String description() {
        return result().description();
    }
}
