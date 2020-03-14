package library.domain.model.retention;


import library.domain.model.holding.Holding;
import library.domain.model.holding.HoldingInStock;
import library.domain.model.holding.HoldingsInStock;
import library.domain.model.reservation.reservation.ReservedBook;

/**
 * 取置
 */
public class Retention {
    HoldingsInStock holdingsInStock;
    Retentions retentions;

    Retentionability retentionability(ReservedBook reservedBook) {
        for (HoldingInStock holdingInStock : holdingsInStock.list()) {
            if (holdingInStock.holding().sameBook(reservedBook.book()) && retentionability(holdingInStock.holding())) {
                return Retentionability.取置可能;
            }
        }
        return Retentionability.取置キャンセル;
    }

    private boolean retentionability(Holding holding) {
        return retentions.notContains(holding);
    }

}
