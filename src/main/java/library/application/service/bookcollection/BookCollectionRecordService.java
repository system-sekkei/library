package library.application.service.bookcollection;

import library.application.repository.BookCollectionRepository;
import library.domain.model.bookcollection.BookCollection;
import org.springframework.stereotype.Service;

/**
 * 蔵書登録サービス
 */
@Service
public class BookCollectionRecordService {
    BookCollectionRepository bookCollectionRepository;

    BookCollectionRecordService(BookCollectionRepository bookCollectionRepository) {
        this.bookCollectionRepository = bookCollectionRepository;
    }

    public void registerBookCollection(BookCollection bookCollection) {
        bookCollectionRepository.registerBookCollection(bookCollection);
    }
}
