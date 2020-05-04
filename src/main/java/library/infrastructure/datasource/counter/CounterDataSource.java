package library.infrastructure.datasource.counter;

import library.application.repository.CounterRepository;
import library.domain.model.book.BookIds;
import library.domain.model.bookonloan.librarycard.*;
import library.domain.model.counter.Counter;
import library.domain.model.item.Catalog;
import library.domain.model.item.Item;
import library.domain.model.item.ItemNumber;
import library.domain.model.retention.RetentionShelf;
import library.infrastructure.datasource.bookonloan.BookOnLoanData;
import library.infrastructure.datasource.bookonloan.BookOnLoanMapper;
import library.infrastructure.datasource.bookonloan.ReturnBookData;
import library.infrastructure.datasource.item.HoldingMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CounterDataSource implements CounterRepository {
    HoldingMapper holdingMapper;
    BookOnLoanMapper bookOnLoanMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public CounterDataSource(HoldingMapper holdingMapper, BookOnLoanMapper bookOnLoanMapper) {
        this.holdingMapper = holdingMapper;
        this.bookOnLoanMapper = bookOnLoanMapper;
    }

    @Override
    public Counter counter(BookIds bookIds) {
        if (bookIds.isEmpty()) {
            return Counter.empty();
        }

        List<Item> items = holdingMapper.selectHoldingsByBookIds(bookIds.asList());
        Catalog catalog = new Catalog(items);

        return new Counter(catalog, libraryCardShelf(catalog), retentionShelf());
    }

    private RetentionShelf retentionShelf() {
        // TODO: 取置情報を取得する
        return RetentionShelf.empty();
    }

    private LibraryCardShelf libraryCardShelf(Catalog catalog) {
        List<BookOnLoanData> bookOnLoansDataList = bookOnLoanMapper.selectByItemNumbers(catalog.holdingsCodes());
        List<ReturnBookData> returnBookDataList = bookOnLoanMapper.selectReturnedBookByItemNumbers(catalog.holdingsCodes());

        List<LibraryCard> libraryCards = catalog.holdingsCodes().stream().map(holdingCode -> {
            List<LoaningRecord> loaningRecords = toLoaningRecords(bookOnLoansDataList, holdingCode);
            List<ReturningRecord> returningRecords = toReturningRecords(returnBookDataList, holdingCode);

            return new LibraryCard(holdingCode, new LoaningHistory(loaningRecords), new ReturningHistory(returningRecords));
        }).collect(Collectors.toList());

        return new LibraryCardShelf(libraryCards);
    }

    private List<LoaningRecord> toLoaningRecords(List<BookOnLoanData> bookOnLoansDataList, ItemNumber itemNumber) {
        return bookOnLoansDataList.stream()
            .filter(bookOnLoanData -> bookOnLoanData.itemNumber().sameValue(itemNumber))
            .map(BookOnLoanData::toLoaningRecord)
            .collect(Collectors.toList());
    }

    private List<ReturningRecord> toReturningRecords(List<ReturnBookData> returnBookDataList, ItemNumber itemNumber) {
        return returnBookDataList.stream()
                .filter(returnBookData -> returnBookData.itemNumber().sameValue(itemNumber))
                .map(ReturnBookData::toReturningRecord)
                .collect(Collectors.toList());
    }
}
