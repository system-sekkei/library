package library.application.repository;

import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookcollection.BookCollectionOnLoan;

/**
 * 蔵書リポジトリ
 */
public interface BookCollectionRepository {
    BookCollectionOnLoan findBookCollectionOnLoan(BookCollectionCode bookCollectionCode);
}
