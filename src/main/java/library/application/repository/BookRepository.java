package library.application.repository;

import library.domain.model.book.bibliography.Book;
import library.domain.model.book.bibliography.BookId;
import library.domain.model.book.bibliography.BookSearchKeyword;
import library.domain.model.book.bibliography.Books;

/**
 * 本リポジトリ
 */
public interface BookRepository {

    Books search(BookSearchKeyword keyword);

    Book findBook(BookId bookId);
}
