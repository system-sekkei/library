package library.application.service.book;

import library.domain.model.book.bibliography.Book;
import library.domain.model.book.bibliography.BookNumber;
import library.domain.model.book.bibliography.Keyword;
import library.domain.model.reservation.availability.BookAvailabilities;
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
    public Book findBook(BookNumber bookNumber) {
        return bookRepository.findBook(bookNumber);
    }

    /**
     * キーワードで本を探す
     */
    public BookAvailabilities search(Keyword keyword) {
        return bookRepository.search(keyword);
    }
}
