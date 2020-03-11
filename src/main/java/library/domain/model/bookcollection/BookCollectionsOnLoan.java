package library.domain.model.bookcollection;

import java.util.List;

/**
 * 貸出中の蔵書リスト
 */
public class BookCollectionsOnLoan {
    List<BookCollectionOnLoan> list;

    BookCollectionsOnLoan(List<BookCollectionOnLoan> list) {
        this.list = list;
    }
}
