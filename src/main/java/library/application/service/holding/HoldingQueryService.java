package library.application.service.holding;

import library.application.repository.HoldingRepository;
import library.domain.model.holding.HoldingCode;
import library.domain.model.holding.HoldingInStock;
import library.domain.model.holding.HoldingOnLoan;
import org.springframework.stereotype.Service;

/**
 * 蔵書参照サービス
 */
@Service
public class HoldingQueryService {
    HoldingRepository holdingRepository;

    HoldingQueryService(HoldingRepository holdingRepository) {
        this.holdingRepository = holdingRepository;
    }

    /**
     * 貸出中の蔵書を取得する
     */
    public HoldingOnLoan findHoldingOnLoan(HoldingCode holdingCode) {
        return holdingRepository.findHoldingOnLoan(holdingCode);
    }

    /**
     * 在庫中の蔵書を取得する
     */
    public HoldingInStock findHoldingInStock(HoldingCode holdingCode) {
        return holdingRepository.findHoldingInStock(holdingCode);
    }
}
