package library.infrastructure.datasource.counter;

import library.application.repository.CounterRepository;
import library.domain.model.book.BookIds;
import library.domain.model.bookonloan.librarycard.LibraryCardShelf;
import library.domain.model.counter.Counter;
import library.domain.model.holding.Catalog;
import library.domain.model.retention.RetentionShelf;
import org.springframework.stereotype.Repository;

@Repository
public class CounterDataSource implements CounterRepository {

    @Override
    public Counter counter(BookIds bookIds) {
        return new Counter(catalog(), libraryCardShelf(), retentionShelf());
    }

    private RetentionShelf retentionShelf() {
        return null;
    }

    private LibraryCardShelf libraryCardShelf() {
        return null;
    }

    private Catalog catalog() {
        return null;
    }
}
