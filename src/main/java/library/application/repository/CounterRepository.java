package library.application.repository;

import library.domain.model.book.bibliography.BookIds;
import library.domain.model.reservation.availability.Availability;

/**
 * カウンターリポジトリ
 */
public interface CounterRepository {
    Availability counter(BookIds bookIds);
}
