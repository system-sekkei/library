package library.application.service.holding;

import library.application.repository.HoldingRepository;
import library.domain.model.book.item.ItemNumber;
import library.domain.model.book.item.HoldingInStock;
import library.domain.model.book.item.HoldingOnLoan;
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
    public HoldingOnLoan findHoldingOnLoan(ItemNumber itemNumber) {
        return holdingRepository.findHoldingOnLoan(itemNumber);
    }

    /**
     * 在庫中の蔵書を取得する
     */
    public HoldingInStock findHoldingInStock(ItemNumber itemNumber) {
        return holdingRepository.findHoldingInStock(itemNumber);
    }
}
