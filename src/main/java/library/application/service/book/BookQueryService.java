package library.application.service.book;

import library.domain.model.material.bibliography.Book;
import library.domain.model.material.bibliography.BookNumber;
import library.domain.model.material.bibliography.Keyword;
import library.domain.model.reservation.loanability.BookLoanabilities;
import org.springframework.stereotype.Service;

/**
 * 資料の参照と検索
 */
@Service
public class BookQueryService {
    BookRepository bookRepository;

    BookQueryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * 資料番号で本を見つける
     */
    public Book findBook(BookNumber bookNumber) {
        return bookRepository.findBook(bookNumber);
    }

    /**
     * キーワードで本を探す
     */
    public BookLoanabilities search(Keyword keyword) {
        return bookRepository.search(keyword);
    }
}
