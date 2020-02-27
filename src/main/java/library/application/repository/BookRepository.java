package library.application.repository;

import library.domain.model.book.Books;
import library.domain.model.book.BookSearchKeyword;

/**
 * 本リポジトリ
 */
public interface BookRepository {

    Books search(BookSearchKeyword keyword);
}
