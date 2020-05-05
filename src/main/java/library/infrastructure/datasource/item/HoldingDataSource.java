package library.infrastructure.datasource.item;

import library.application.repository.HoldingRepository;
import library.domain.model.book.item.Item;
import library.domain.model.book.item.ItemNumber;
import library.domain.model.book.item.HoldingInStock;
import library.domain.model.book.item.HoldingOnLoan;
import library.infrastructure.datasource.bookonloan.BookOnLoanMapper;
import org.springframework.stereotype.Repository;

@Repository
public class HoldingDataSource implements HoldingRepository {
    HoldingMapper holdingMapper;
    BookOnLoanMapper bookOnLoanMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public HoldingDataSource(HoldingMapper holdingMapper, BookOnLoanMapper bookOnLoanMapper) {
        this.holdingMapper = holdingMapper;
        this.bookOnLoanMapper = bookOnLoanMapper;
    }

    @Override
    public HoldingOnLoan findHoldingOnLoan(ItemNumber itemNumber) {
        Item item = holdingMapper.selectHolding(itemNumber);

        if (bookOnLoanMapper.selectByItemNumber(itemNumber).isEmpty()) {
            throw new IllegalArgumentException(String.format("現在貸し出されていない蔵書です。蔵書コード：%s", itemNumber));
        }

        return new HoldingOnLoan(item);
    }

    @Override
    public HoldingInStock findHoldingInStock(ItemNumber itemNumber) {
        Item item = holdingMapper.selectHolding(itemNumber);

        if (bookOnLoanMapper.selectByItemNumber(itemNumber).isPresent()) {
            throw new IllegalArgumentException(String.format("現在在庫にない蔵書です。蔵書コード：%s", itemNumber));
        }

        return new HoldingInStock(item);
    }
}
