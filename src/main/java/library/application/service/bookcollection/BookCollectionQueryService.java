package library.application.service.bookcollection;

import library.application.repository.BookCollectionRepository;
import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookcollection.BookCollectionOnLoan;
import org.springframework.stereotype.Service;

/**
 * 蔵書参照サービス
 */
@Service
public class BookCollectionQueryService {
    BookCollectionRepository bookCollectionRepository;

    BookCollectionQueryService(BookCollectionRepository bookCollectionRepository) {
        this.bookCollectionRepository = bookCollectionRepository;
    }

    /**
     * 蔵書取得
     */
     // TODO: 貸出中の蔵書取得に修正する？
    public BookCollectionOnLoan findBookCollection(BookCollectionCode bookCollectionCode) {
        return bookCollectionRepository.findBookCollection(bookCollectionCode);
    }
}
