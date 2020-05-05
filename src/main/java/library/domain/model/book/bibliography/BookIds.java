package library.domain.model.book.bibliography;

import java.util.List;

/**
 * 本IDのリスト
 */
public class BookIds {
    List<BookId> list;

    public BookIds(List<BookId> list) {
        this.list = list;
    }

    public List<BookId> asList() {
        return list;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
