package library.infrastructure.datasource.bookcollection;

import library.application.repository.BookCollectionRepository;
import library.domain.model.bookcollection.BookCollection;
import library.domain.model.bookcollection.BookCollectionCode;
import org.springframework.stereotype.Repository;

@Repository
public class BookCollectionDataSource implements BookCollectionRepository {
    BookCollectionMapper mapper;

    public BookCollectionDataSource(BookCollectionMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BookCollection findBookCollection(BookCollectionCode bookCollectionCode) {
        return mapper.selectBookCollection(bookCollectionCode);
    }
}
