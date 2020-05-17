package library.infrastructure.datasource.book;

import library.application.repository.BookRepository;
import library.domain.model.item.bibliography.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDataSource implements BookRepository {
    BookMapper bookMapper;


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public BookDataSource(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public Books search(Keyword keyword) {
        List<Book> books = bookMapper.searchBooks(keyword, NumberOfBook.MAX_TO_SHOW + 1);
        return new Books(books);
    }

    @Override
    public Book findBook(BookNumber bookNumber) {
        return bookMapper.findBook(bookNumber);
    }
}
