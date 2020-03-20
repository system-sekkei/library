package library.infrastructure.datasource.holding;

import library.application.repository.HoldingRepository;
import library.domain.model.book.BookIds;
import library.domain.model.holding.*;
import library.infrastructure.datasource.bookonloan.BookOnLoanMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class HoldingDataSource implements HoldingRepository {
    HoldingMapper holdingMapper;
    BookOnLoanMapper bookOnLoanMapper;

    public HoldingDataSource(HoldingMapper holdingMapper, BookOnLoanMapper bookOnLoanMapper) {
        this.holdingMapper = holdingMapper;
        this.bookOnLoanMapper = bookOnLoanMapper;
    }

    @Override
    public HoldingOnLoan findHoldingOnLoan(HoldingCode holdingCode) {
        Holding holding = holdingMapper.selectHolding(holdingCode);

        if (bookOnLoanMapper.selectByHoldingCode(holdingCode).isEmpty()) {
            throw new IllegalArgumentException(String.format("現在貸し出されていない蔵書です。蔵書コード：%s", holdingCode));
        }

        return new HoldingOnLoan(holding);
    }

    @Override
    public HoldingInStock findHoldingInStock(HoldingCode holdingCode) {
        Holding holding = holdingMapper.selectHolding(holdingCode);

        if (bookOnLoanMapper.selectByHoldingCode(holdingCode).isPresent()) {
            throw new IllegalArgumentException(String.format("現在在庫にない蔵書です。蔵書コード：%s", holdingCode));
        }

        return new HoldingInStock(holding);
    }

    @Override
    public HoldingsInStock findHoldingsInStockByBookIds(BookIds bookIds) {
        if (bookIds.isEmpty()) {
            return new HoldingsInStock(List.of());
        }

        List<HoldingInStock> holdingInStocks =
            holdingMapper.selectHoldingsInStockByBookIds(bookIds.asList()).stream().map(HoldingInStock::new).collect(Collectors.toList());
        return new HoldingsInStock(holdingInStocks);
    }
}
