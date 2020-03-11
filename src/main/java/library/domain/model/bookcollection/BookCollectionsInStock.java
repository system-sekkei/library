package library.domain.model.bookcollection;

import java.util.List;

/**
 * 在庫中の蔵書リスト
 */
public class BookCollectionsInStock {
    List<BookCollectionInStock> list;

    BookCollectionsInStock(List<BookCollectionInStock> list) {
        this.list = list;
    }
}
