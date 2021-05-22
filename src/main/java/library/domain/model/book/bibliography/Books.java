package library.domain.model.book.bibliography;

import java.util.Collections;
import java.util.List;

/**
 * 書籍のリスト
 */
public class Books {
    List<Book> list;

    public Books(List<Book> list) {
        this.list = list;
    }

    public String count() {
        return new NumberOfBook(list.size()).show();
    }
    public int size() {
        return list.size();
    }
    public List<Book> asList() {
        return Collections.unmodifiableList(list);
    }
}
