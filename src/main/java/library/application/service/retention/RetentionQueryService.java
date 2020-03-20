package library.application.service.retention;

import library.application.repository.HoldingRepository;
import library.domain.model.book.BookIds;
import library.domain.model.holding.HoldingsInStock;
import library.domain.model.reservation.reservation.ReservedBooks;
import library.domain.model.retention.Retention;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 取置参照サービス
 */
@Service
public class RetentionQueryService {
    HoldingRepository holdingRepository;

    public RetentionQueryService(HoldingRepository holdingRepository) {
        this.holdingRepository = holdingRepository;
    }

    public Retention retention(ReservedBooks reservedBooks) {
        HoldingsInStock holdingsInStock =
            holdingRepository.findHoldingsInStockByBookIds(reservedBooks.bookIds());
        // TODO: Retentions取得

        return new Retention(holdingsInStock, null);
    }
}
