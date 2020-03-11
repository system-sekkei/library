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
     * 貸出中の蔵書取得
     */
    public BookCollectionOnLoan findBookCollectionOnLoan(BookCollectionCode bookCollectionCode) {
        return bookCollectionRepository.findBookCollectionOnLoan(bookCollectionCode);
    }
}
