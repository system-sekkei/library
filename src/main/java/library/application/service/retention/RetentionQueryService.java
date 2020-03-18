package library.application.service.retention;

import library.application.repository.HoldingRepository;
import library.domain.model.holding.HoldingsInStock;
import library.domain.model.retention.Retention;
import org.springframework.stereotype.Service;

/**
 * 取置参照サービス
 */
@Service
public class RetentionQueryService {
    HoldingRepository holdingRepository;

    public RetentionQueryService(HoldingRepository holdingRepository) {
        this.holdingRepository = holdingRepository;
    }

    public Retention retention() {
        HoldingsInStock holdingsInStock = holdingRepository.allHoldingsInStock();

        return new Retention(holdingsInStock, null);
    }
}
