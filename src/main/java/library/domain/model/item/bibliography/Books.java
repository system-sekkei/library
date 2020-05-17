package library.domain.model.item.bibliography;

import java.util.Collections;
import java.util.List;

/**
 * 本のリスト
 */
public class Books {
    List<Book> list;

    public Books(List<Book> list) {
        this.list = list;
    }

    public String count() {
        return new NumberOfBook(list.size()).show();
    }

    public List<Book> asList() {
        return Collections.unmodifiableList(list);
    }
}
