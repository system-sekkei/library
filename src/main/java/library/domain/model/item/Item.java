package library.domain.model.item;

import library.domain.model.item.bibliography.Book;
import library.domain.model.item.bibliography.BookMatching;

import static library.domain.model.item.bibliography.BookMatching.一致;
import static library.domain.model.item.bibliography.BookMatching.不一致;

/**
 * 蔵書
 */
public class Item {
    ItemNumber itemNumber;
    Book book;
    ItemStatus status; // TODO 現在は未使用：明示的に持つべきか検討する

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

    public BookMatching isSameBook(Book other) {
        boolean result = this.book.isSameBook(other);
        return result ? 一致 : 不一致;
    }

    public String show() {
        return String.format("[%s] %s",
                itemNumber.toString(), book.show());
    }
    @Override
    public String toString() {
        return "Item{" +
                "itemNumber=" + itemNumber +
                ", book=" + book +
                ", status=" + status +
                '}';
    }
}
