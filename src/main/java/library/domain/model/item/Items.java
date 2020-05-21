package library.domain.model.item;

import library.domain.model.item.bibliography.Book;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 蔵書目録
 */
public class Items {
    List<Item> list;

    public Items(List<Item> list) {
        this.list = list;
    }

    public List<Item> list() {
        return list;
    }

    public static Items empty() {
        return new Items(List.of());
    }

    public Items findItemsByBook(Book book) {
        List<Item> result = list.stream().filter(item -> item.book.isSameBook(book)).collect(Collectors.toList());
        return new Items(result);
    }

    public List<ItemNumber> itemNumbers() {
        return list.stream().map(Item::itemNumber).collect(Collectors.toList());
    }
}
