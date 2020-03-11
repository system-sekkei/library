package library.infrastructure.datasource.holding;

import library.application.repository.HoldingRepository;
import library.domain.model.holding.Holding;
import library.domain.model.holding.HoldingCode;
import library.domain.model.holding.HoldingInStock;
import library.domain.model.holding.HoldingOnLoan;
import org.springframework.stereotype.Repository;

@Repository
public class HoldingDataSource implements HoldingRepository {
    HoldingMapper mapper;

    public HoldingDataSource(HoldingMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public HoldingOnLoan findHoldingOnLoan(HoldingCode holdingCode) {
        // TODO: 貸出中の蔵書を返却するようにする
        Holding holding = mapper.selectHolding(holdingCode);
        return new HoldingOnLoan(holding);
    }

    @Override
    public HoldingInStock findHoldingInStock(HoldingCode holdingCode) {
        // TODO: 在庫中の蔵書を返却するようにする
        Holding holding = mapper.selectHolding(holdingCode);
        return new HoldingInStock(holding);
    }
}
