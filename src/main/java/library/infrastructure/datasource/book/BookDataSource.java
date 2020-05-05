package library.infrastructure.datasource.book;

import library.application.repository.BookRepository;
import library.domain.model.book.bibliography.Book;
import library.domain.model.book.bibliography.BookId;
import library.domain.model.book.bibliography.BookSearchKeyword;
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
    public Books search(BookSearchKeyword keyword) {
        List<Book> books = bookMapper.searchBooks(keyword);
        return new Books(books);
    }

    @Override
    public Book findBook(BookId bookId) {
        return bookMapper.findBook(bookId);
    }
}
