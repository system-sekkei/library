package library.domain.model.holding;

import library.domain.model.book.Book;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 目録
 */
public class Catalog {
    List<Holding> list;

    public Catalog(List<Holding> list) {
        this.list = list;
    }

    public List<Holding> list() {
        return list;
    }

    public Catalog findHoldingsByBook(Book book) {
        List<Holding> holdings = list.stream().filter(holding -> holding.book.sameBook(book)).collect(Collectors.toList());
        return new Catalog(holdings);
    }
}
