package library.domain.model.item;

import library.domain.model.book.Book;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 目録
 */
public class Catalog {
    List<Item> list;

    public Catalog(List<Item> list) {
        this.list = list;
    }

    public List<Item> list() {
        return list;
    }

    public static Catalog empty() {
        return new Catalog(List.of());
    }

    public Catalog findHoldingsByBook(Book book) {
        List<Item> items = list.stream().filter(holding -> holding.book.sameBook(book)).collect(Collectors.toList());
        return new Catalog(items);
    }

    public List<ItemNumber> holdingsCodes() {
        return list.stream().map(Item::itemNumber).collect(Collectors.toList());
    }
}
