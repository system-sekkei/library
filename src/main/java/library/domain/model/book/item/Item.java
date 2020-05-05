package library.domain.model.book.item;

import library.domain.model.book.bibliography.Book;

/**
 * 蔵書
 */
public class Item {
    ItemNumber itemNumber;
    Book book;

    @Deprecated
    Item() {
    }

    public Item(ItemNumber itemNumber, Book book) {
        this.itemNumber = itemNumber;
        this.book = book;
    }

    public ItemNumber itemNumber() {
        return itemNumber;
    }

    // TODO: 予約本を探すメソッドにしたい (ReservedBookを受けるようにしたい)
    public boolean sameBook(Book other) {
        return this.book.sameBook(other);
    }

}
