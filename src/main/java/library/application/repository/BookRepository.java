package library.application.repository;

import library.domain.model.book.Books;
import library.domain.model.book.SearchKeyword;

/**
 * 本リポジトリ
 */
public interface BookRepository {

    Books search(SearchKeyword keyword);
}
