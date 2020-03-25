package library.infrastructure.datasource.counter;

import library.application.repository.CounterRepository;
import library.domain.model.book.BookIds;
import library.domain.model.bookonloan.librarycard.LibraryCardShelf;
import library.domain.model.counter.Counter;
import library.domain.model.holding.Catalog;
import library.domain.model.holding.Holding;
import library.domain.model.retention.RetentionShelf;
import library.infrastructure.datasource.bookonloan.BookOnLoanData;
import library.infrastructure.datasource.bookonloan.BookOnLoanMapper;
import library.infrastructure.datasource.bookonloan.ReturnBookData;
import library.infrastructure.datasource.holding.HoldingMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CounterDataSource implements CounterRepository {
    HoldingMapper holdingMapper;
    BookOnLoanMapper bookOnLoanMapper;

    public CounterDataSource(HoldingMapper holdingMapper, BookOnLoanMapper bookOnLoanMapper) {
        this.holdingMapper = holdingMapper;
        this.bookOnLoanMapper = bookOnLoanMapper;
    }

    @Override
    public Counter counter(BookIds bookIds) {
        Catalog catalog = catalog(bookIds);
        return new Counter(catalog, libraryCardShelf(catalog), retentionShelf());
    }

    private RetentionShelf retentionShelf() {
        return null;
    }

    private LibraryCardShelf libraryCardShelf(Catalog catalog) {
        List<BookOnLoanData> bookOnLoansDataList = bookOnLoanMapper.selectByHoldingCodes(catalog.holdingsCodes());
        List<ReturnBookData> returnBookDataList = bookOnLoanMapper.selectReturnedBookByHoldingCodes(catalog.holdingsCodes());

        return null;
    }

    private Catalog catalog(BookIds bookIds) {
        List<Holding> holdings = holdingMapper.selectHoldingsByBookIds(bookIds.asList());
        return new Catalog(holdings);
    }
}
