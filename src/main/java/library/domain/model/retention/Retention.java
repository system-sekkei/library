package library.domain.model.retention;


import library.domain.model.holding.Holding;
import library.domain.model.holding.HoldingInStock;
import library.domain.model.holding.HoldingsInStock;
import library.domain.model.reservation.reservation.ReservedBook;
import library.domain.model.reservation.reservation.ReservedBooks;

import java.util.ArrayList;
import java.util.List;

/**
 * 取置
 */
public class Retention {
    HoldingsInStock holdingsInStock;
    Retentions retentions;

    public RetentionableReservedBooks retentionableReservedBooks(ReservedBooks reservedBooks) {
        List<ReservedBook> list = new ArrayList<>();
        for (ReservedBook reservedBook : reservedBooks.list()) {
            if (retentionability(reservedBook) == Retentionability.対象) {
                list.add(reservedBook);
            }
        }

        return new RetentionableReservedBooks(list);
    }

    public Retentionability retentionability(ReservedBook reservedBook) {
        for (HoldingInStock holdingInStock : holdingsInStock.list()) {
            if (holdingInStock.holding().sameBook(reservedBook.book()) && retentionability(holdingInStock.holding())) {
                return Retentionability.対象;
            }
        }
        return Retentionability.対象外;
    }

    private boolean retentionability(Holding holding) {
        return retentions.notContains(holding);
    }

}
