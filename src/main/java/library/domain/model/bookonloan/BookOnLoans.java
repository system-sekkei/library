package library.domain.model.bookonloan;

import java.util.List;

/**
 * 貸出図書リスト
 */
public class BookOnLoans {
    List<BookOnLoan> list;

    public BookOnLoans(List<BookOnLoan> list) {
        this.list = list;
    }
}
