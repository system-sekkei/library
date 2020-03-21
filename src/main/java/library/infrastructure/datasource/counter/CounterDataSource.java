package library.infrastructure.datasource.counter;

import library.application.repository.CounterRepository;
import library.domain.model.book.BookId;
import library.domain.model.counter.Counters;
import org.springframework.stereotype.Repository;

@Repository
public class CounterDataSource implements CounterRepository {

    @Override
    public Counters counters(BookId bookId) {
        // TODO:
        return null;
    }
}
