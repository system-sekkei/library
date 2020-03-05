package library.application.service.reservation;

import library.application.repository.BookRepository;
import library.domain.model.book.Book;
import library.domain.model.book.BookId;
import library.domain.model.book.Books;
import library.domain.model.book.BookSearchKeyword;
import org.springframework.stereotype.Service;

/**
 * 本参照サービス
 */
@Service
public class BookQueryService {
    BookRepository bookRepository;

    BookQueryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * 本取得
     */
    public Book findBook(BookId bookId) {
        return bookRepository.findBook(bookId);
    }

    /**
     * 本検索
     */
    public Books search(BookSearchKeyword keyword) {
        return bookRepository.search(keyword);
    }
}
