package library.application.service.bookcollection;

import library.application.repository.BookCollectionRepository;
import library.domain.model.bookcollection.BookCollection;
import library.domain.model.bookcollection.BookCollectionCode;
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
    public BookCollection findBookCollection(BookCollectionCode bookCollectionCode) {
        return bookCollectionRepository.findBookCollection(bookCollectionCode);
    }
}
