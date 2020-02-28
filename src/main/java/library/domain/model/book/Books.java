package library.domain.model.book;

import java.util.List;

/**
 * 本リスト
 */
public class Books {
    List<Book> list;

    public Books(List<Book> list) {
        this.list = list;
    }

    public NumberOfBook size() {
        return new NumberOfBook(list.size());
    }

    public List<Book> asList() {
        return list;
    }
}
