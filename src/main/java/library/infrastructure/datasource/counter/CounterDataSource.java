package library.infrastructure.datasource.counter;

import library.application.repository.CounterRepository;
import library.domain.model.book.bibliography.BookIds;
import library.domain.model.book.item.Item;
import library.domain.model.book.item.Items;
import library.domain.model.loan.history.LoanHistory;
import library.domain.model.loan.history.WholeLoanHistory;
import library.domain.model.loan.loan.Loan;
import library.domain.model.loan.loan.Loans;
import library.domain.model.loan.returned.Returned;
import library.domain.model.loan.returned.Returns;
import library.domain.model.reservation.availability.Availability;
import library.domain.model.reservation.retention.RetentionShelf;
import library.infrastructure.datasource.item.ItemMapper;
import library.infrastructure.datasource.loan.LoanMapper;
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
        List<Loan> loans = loanMapper.selectByItemNumbers(items.holdingsCodes());
        List<Returned> returns = loanMapper.selectReturnedByItemNumbers(items.holdingsCodes());

        List<LoanHistory> loanHistories = items.holdingsCodes().stream().map(itemNumber -> {
            return new LoanHistory(itemNumber, new Loans(loans), new Returns(returns));
        }).collect(Collectors.toList());

        return new WholeLoanHistory(loanHistories);
    }
}
