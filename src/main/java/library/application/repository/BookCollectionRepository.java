package library.application.repository;

import library.domain.model.bookcollection.BookCollection;
import library.domain.model.bookcollection.BookCollectionCode;

/**
 * 蔵書リポジトリ
 */
public interface BookCollectionRepository {
    void registerBookCollection(BookCollection bookCollection);

    BookCollection findBookCollection(BookCollectionCode bookCollectionCode);
}
