package library.domain.model.material.bibliography;

import java.util.List;

/**
 * 資料番号のリスト
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
