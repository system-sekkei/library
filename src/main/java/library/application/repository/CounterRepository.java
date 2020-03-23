package library.application.repository;

import library.domain.model.book.BookId;
import library.domain.model.counter.Counter;

/**
 * カウンターリポジトリ
 */
public interface CounterRepository {
    Counter counters(BookId bookId);
}
