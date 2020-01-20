package library.application.service.bookcollection;

import library.application.repository.BookCollectionRepository;
import library.domain.model.bookcollection.BookCollections;
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
     * 蔵書一覧
     */
    public BookCollections findBookCollections() {
        return bookCollectionRepository.findBookCollections();
    }
}
