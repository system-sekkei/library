package library.application.repository;

import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookcollection.BookCollectionOnLoan;

/**
 * 蔵書リポジトリ
 */
public interface BookCollectionRepository {
    // TODO: 貸出中の蔵書取得と在庫中の蔵書取得にわかれる
    BookCollectionOnLoan findBookCollection(BookCollectionCode bookCollectionCode);
}
