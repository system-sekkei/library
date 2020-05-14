package library.infrastructure.datasource.counter;

import library.application.repository.CounterRepository;
import library.domain.model.book.bibliography.BookIds;
import library.domain.model.book.item.Item;
import library.domain.model.book.item.ItemNumber;
import library.domain.model.book.item.Items;
import library.domain.model.loan.history.*;
import library.domain.model.loan.loan.Loan;
import library.domain.model.reservation.availability.Availability;
import library.domain.model.reservation.retention.RetentionShelf;
import library.infrastructure.datasource.item.ItemMapper;
import library.infrastructure.datasource.loan.LoanMapper;
import library.infrastructure.datasource.loan.ReturnData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CounterDataSource implements CounterRepository {
    ItemMapper itemMapper;
    LoanMapper loanMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public CounterDataSource(ItemMapper itemMapper, LoanMapper loanMapper) {
        this.itemMapper = itemMapper;
        this.loanMapper = loanMapper;
    }

    @Override
    public Availability counter(BookIds bookIds) {
        if (bookIds.isEmpty()) {
            return Availability.empty();
        }

        List<Item> items = itemMapper.selectItemsByBookIds(bookIds.asList());
        Items catalog = new Items(items);

        return new Availability(catalog, libraryCardShelf(catalog), retentionShelf());
    }

    private RetentionShelf retentionShelf() {
        // TODO: 取置情報を取得する
        return RetentionShelf.empty();
    }

    private WholeLoanHistory libraryCardShelf(Items items) {
        List<Loan> bookOnLoansDataList = loanMapper.selectByItemNumbers(items.holdingsCodes());
        List<ReturnData> returnDataList = loanMapper.selectReturnedByItemNumbers(items.holdingsCodes());

        List<LoanHistory> loanHistories = items.holdingsCodes().stream().map(holdingCode -> {
            List<LoanRecord> loanRecords = toLoaningRecords(bookOnLoansDataList, holdingCode);
            List<ReturnRecord> returnRecords = toReturningRecords(returnDataList, holdingCode);

            return new LoanHistory(holdingCode, new LoanRecords(loanRecords), new ReturnRecords(returnRecords));
        }).collect(Collectors.toList());

        return new WholeLoanHistory(loanHistories);
    }

    private List<LoanRecord> toLoaningRecords(List<Loan> bookOnLoansDataList, ItemNumber itemNumber) {
        return bookOnLoansDataList.stream()
            .filter(loanData -> loanData.itemNumber().sameValue(itemNumber))
            .map(Loan::toLoaningRecord)
            .collect(Collectors.toList());
    }

    private List<ReturnRecord> toReturningRecords(List<ReturnData> returnDataList, ItemNumber itemNumber) {
        return returnDataList.stream()
                .filter(returnData -> returnData.itemNumber().sameValue(itemNumber))
                .map(ReturnData::toReturningRecord)
                .collect(Collectors.toList());
    }
}
