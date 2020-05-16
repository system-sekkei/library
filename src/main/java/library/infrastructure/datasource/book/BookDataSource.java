package library.infrastructure.datasource.book;

import library.application.repository.BookRepository;
import library.domain.model.book.bibliography.Book;
import library.domain.model.book.bibliography.BookNumber;
import library.domain.model.book.bibliography.Keyword;
import library.domain.model.book.bibliography.Books;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDataSource implements BookRepository {
    BookMapper bookMapper;

    public BookDataSource(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public Books search(Keyword keyword) {
        List<Book> books = bookMapper.searchBooks(keyword);
        return new Books(books);
    }

    @Override
    public Book findBook(BookNumber bookNumber) {
        return bookMapper.findBook(bookNumber);
    }
}
