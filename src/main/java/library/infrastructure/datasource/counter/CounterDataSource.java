package library.infrastructure.datasource.counter;

import library.application.repository.CounterRepository;
import library.domain.model.book.bibliography.BookIds;
import library.domain.model.loan.history.*;
import library.domain.model.reservation.availability.Availability;
import library.domain.model.book.item.Items;
import library.domain.model.book.item.Item;
import library.domain.model.book.item.ItemNumber;
import library.domain.model.reservation.retention.RetentionShelf;
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
    public Availability counter(BookIds bookIds) {
        if (bookIds.isEmpty()) {
            return Availability.empty();
        }

        List<Item> items = holdingMapper.selectHoldingsByBookIds(bookIds.asList());
        Items catalog = new Items(items);

        return new Availability(catalog, libraryCardShelf(catalog), retentionShelf());
    }

    private RetentionShelf retentionShelf() {
        // TODO: 取置情報を取得する
        return RetentionShelf.empty();
    }

    private WholeLoanHistory libraryCardShelf(Items items) {
        List<BookOnLoanData> bookOnLoansDataList = bookOnLoanMapper.selectByItemNumbers(items.holdingsCodes());
        List<ReturnBookData> returnBookDataList = bookOnLoanMapper.selectReturnedBookByItemNumbers(items.holdingsCodes());

        List<LoanHistory> loanHistories = items.holdingsCodes().stream().map(holdingCode -> {
            List<LoaningRecord> loaningRecords = toLoaningRecords(bookOnLoansDataList, holdingCode);
            List<ReturningRecord> returningRecords = toReturningRecords(returnBookDataList, holdingCode);

            return new LoanHistory(holdingCode, new LoaningHistory(loaningRecords), new ReturningHistory(returningRecords));
        }).collect(Collectors.toList());

        return new WholeLoanHistory(loanHistories);
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
