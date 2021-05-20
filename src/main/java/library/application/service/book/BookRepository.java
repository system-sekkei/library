package library.application.service.book;

import library.domain.model.item.bibliography.Book;
import library.domain.model.item.bibliography.BookNumber;
import library.domain.model.item.bibliography.Keyword;
import library.domain.model.reservation.availability.BookAvailabilities;

/**
 * 本リポジトリ
 */
public interface BookRepository {

    BookAvailabilities search(Keyword keyword);

    Book findBook(BookNumber bookNumber);
}
