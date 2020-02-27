package library.infrastructure.datasource.book;

import library.application.repository.BookRepository;
import library.domain.model.book.Books;
import library.domain.model.book.SearchKeyword;
import org.springframework.stereotype.Repository;

@Repository
public class BookDataSource implements BookRepository {

    @Override
    public Books search(SearchKeyword keyword) {
        return null;
    }
}
