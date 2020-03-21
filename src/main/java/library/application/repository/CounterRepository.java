package library.application.repository;

import library.domain.model.book.BookId;
import library.domain.model.counter.Counters;

/**
 * カウンターリポジトリ
 */
public interface CounterRepository {
    Counters counters(BookId bookId);
}
