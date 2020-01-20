package library.infrastructure.datasource.bookcollection;

import library.application.repository.BookCollectionRepository;
import library.domain.model.bookcollection.BookCollection;
import library.domain.model.bookcollection.BookCollections;
import org.springframework.stereotype.Repository;

@Repository
public class BookCollectionDataSource implements BookCollectionRepository {
    @Override
    public void registerBookCollection(BookCollection bookCollection) {

    }

    @Override
    public BookCollections findBookCollections() {
        return null;
    }
}
