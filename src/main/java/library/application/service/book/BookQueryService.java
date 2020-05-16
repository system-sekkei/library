package library.application.service.book;

import library.application.repository.BookRepository;
import library.domain.model.book.bibliography.Book;
import library.domain.model.book.bibliography.BookId;
import library.domain.model.book.bibliography.BookSearchKeyword;
import library.domain.model.book.bibliography.Books;
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
     * 書籍番号で本を見つける
     */
    public Book findBook(BookId bookId) {
        return bookRepository.findBook(bookId);
    }

    /**
     * キーワードで本を探す
     */
    public Books search(BookSearchKeyword keyword) {
        return bookRepository.search(keyword);
    }
}
