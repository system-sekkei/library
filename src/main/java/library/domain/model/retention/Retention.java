package library.domain.model.retention;


import library.domain.model.holding.Holding;
import library.domain.model.holding.HoldingInStock;
import library.domain.model.holding.HoldingsInStock;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.Reservations;

import java.util.ArrayList;
import java.util.List;

/**
 * 取置
 */
public class Retention {
    HoldingsInStock holdingsInStock;
    Retentions retentions;

    public Retention(HoldingsInStock holdingsInStock, Retentions retentions) {
        this.holdingsInStock = holdingsInStock;
        this.retentions = retentions;
    }

    public RetentionableReservedBooks retentionableReservedBooks(Reservations reservations) {
        List<Reservation> list = new ArrayList<>();
        for (Reservation reservation : reservations.asList()) {
            if (retentionability(reservation) == Retentionability.対象) {
                list.add(reservation);
            }
        }

        return new RetentionableReservedBooks(list);
    }

    public Retentionability retentionability(Reservation reservation) {
        for (HoldingInStock holdingInStock : holdingsInStock.list()) {
            if (holdingInStock.holding().sameBook(reservation.reservedBook().book()) && retentionability(holdingInStock.holding())) {
                return Retentionability.対象;
            }
        }
        return Retentionability.対象外;
    }

    private boolean retentionability(Holding holding) {
        return retentions.notContains(holding);
    }

}
