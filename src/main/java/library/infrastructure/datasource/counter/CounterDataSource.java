package library.infrastructure.datasource.counter;

import library.application.repository.CounterRepository;
import library.domain.model.book.BookId;
import library.domain.model.counter.Counter;
import org.springframework.stereotype.Repository;

@Repository
public class CounterDataSource implements CounterRepository {

    @Override
    public Counter counters(BookId bookId) {
        // TODO:
        return null;
    }
}
