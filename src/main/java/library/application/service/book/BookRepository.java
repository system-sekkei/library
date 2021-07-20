package library.application.service.book;

import library.domain.model.book.bibliography.Book;
import library.domain.model.book.bibliography.BookNumber;
import library.domain.model.book.bibliography.Keyword;
import library.domain.model.reservation.loanability.BookLoanabilities;

/**
 * 本リポジトリ
 */
public interface BookRepository {

    BookLoanabilities search(Keyword keyword);

    Book findBook(BookNumber bookNumber);
}
