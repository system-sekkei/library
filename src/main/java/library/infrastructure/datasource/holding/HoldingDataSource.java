package library.infrastructure.datasource.holding;

import library.application.repository.HoldingRepository;
import library.domain.model.holding.Holding;
import library.domain.model.holding.HoldingCode;
import library.domain.model.holding.HoldingInStock;
import library.domain.model.holding.HoldingOnLoan;
import library.infrastructure.datasource.bookonloan.BookOnLoanMapper;
import org.springframework.stereotype.Repository;

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
}
