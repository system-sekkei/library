package library.application.repository;

import library.domain.model.book.BookIds;
import library.domain.model.counter.Counter;

/**
 * カウンターリポジトリ
 */
public interface CounterRepository {
    Counter counter(BookIds bookIds);
}
