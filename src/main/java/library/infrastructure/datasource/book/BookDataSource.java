package library.infrastructure.datasource.book;

import library.application.service.book.BookRepository;
import library.domain.model.material.bibliography.*;
import library.domain.model.reservation.loanability.BookLoanabilities;
import library.domain.model.reservation.loanability.BookLoanability;
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
    public BookLoanabilities search(Keyword keyword) {
        List<BookLoanability> books = bookMapper.searchBooks(keyword, NumberOfBook.MAX_TO_SHOW + 1);
        return new BookLoanabilities(books);
    }

    @Override
    public Book findBook(BookNumber bookNumber) {
        return bookMapper.findBook(bookNumber);
    }
}
