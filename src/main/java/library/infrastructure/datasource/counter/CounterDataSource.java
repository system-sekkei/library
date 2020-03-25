package library.infrastructure.datasource.counter;

import library.application.repository.CounterRepository;
import library.domain.model.book.BookIds;
import library.domain.model.bookonloan.librarycard.LibraryCardShelf;
import library.domain.model.counter.Counter;
import library.domain.model.holding.Catalog;
import library.domain.model.holding.Holding;
import library.domain.model.retention.RetentionShelf;
import library.infrastructure.datasource.holding.HoldingMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CounterDataSource implements CounterRepository {
    HoldingMapper holdingMapper;

    public CounterDataSource(HoldingMapper holdingMapper) {
        this.holdingMapper = holdingMapper;
    }

    @Override
    public Counter counter(BookIds bookIds) {
        Catalog catalog = catalog(bookIds);
        return new Counter(catalog, libraryCardShelf(), retentionShelf());
    }

    private RetentionShelf retentionShelf() {
        return null;
    }

    private LibraryCardShelf libraryCardShelf() {
        return null;
    }

    private Catalog catalog(BookIds bookIds) {
        List<Holding> holdings = holdingMapper.selectHoldingsByBookIds(bookIds.asList());
        return new Catalog(holdings);
    }
}
