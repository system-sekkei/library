package library.domain.model.book.bibliography;

import java.util.List;

/**
 * 書籍番号のリスト
 */
public class BookNumbers {
    List<BookNumber> list;

    public BookNumbers(List<BookNumber> list) {
        this.list = list;
    }

    public List<BookNumber> asList() {
        return list;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
