package library.infrastructure.datasource.bookcollection;

import library.application.repository.BookCollectionRepository;
import library.domain.model.bookcollection.BookCollection;
import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookcollection.BookCollectionInStock;
import library.domain.model.bookcollection.BookCollectionOnLoan;
import org.springframework.stereotype.Repository;

@Repository
public class BookCollectionDataSource implements BookCollectionRepository {
    BookCollectionMapper mapper;

    public BookCollectionDataSource(BookCollectionMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BookCollectionOnLoan findBookCollectionOnLoan(BookCollectionCode bookCollectionCode) {
        // TODO: 貸出中の蔵書を返却するようにする
        BookCollection bookCollection = mapper.selectBookCollection(bookCollectionCode);
        return new BookCollectionOnLoan(bookCollection);
    }

    @Override
    public BookCollectionInStock findBookCollectionInStock(BookCollectionCode bookCollectionCode) {
        // TODO: 在庫中の蔵書を返却するようにする
        BookCollection bookCollection = mapper.selectBookCollection(bookCollectionCode);
        return new BookCollectionInStock(bookCollection);
    }
}
