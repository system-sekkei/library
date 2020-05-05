package library.domain.model.reservation.availability;

import library.domain.model.loan.history.WholeLoanHistory;
import library.domain.model.item.Items;
import library.domain.model.item.Item;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.Reservations;
import library.domain.model.reservation.reservation.ReservedBook;
import library.domain.model.reservation.retention.RetentionShelf;
import library.domain.model.reservation.retention.RetentionableReservations;

import java.util.ArrayList;
import java.util.List;

/**
 * 取置可否
 */
public class Availability {
    Items items;
    WholeLoanHistory loans;
    RetentionShelf retentions;

    public Availability(Items items, WholeLoanHistory loans, RetentionShelf retentions) {
        this.items = items;
        this.loans = loans;
        this.retentions = retentions;
    }

    public static Availability empty() {
        return new Availability(Items.empty(), WholeLoanHistory.empty(), RetentionShelf.empty());
    }

    public RetentionableReservations retentionableReservedBooks(Reservations reservations) {
        List<Reservation> list = new ArrayList<>();
        for (Reservation reservation : reservations.asList()) {
            if (stockStatus(reservation.reservedBook()) == StockStatus.在庫あり) {
                list.add(reservation);
            }
        }

        return new RetentionableReservations(list);
    }

    private StockStatus stockStatus(ReservedBook reservedBook) {
        Items sameBookHoldings = items.findItemsByBook(reservedBook.book());

        for (Item item : sameBookHoldings.list()) {
            if (loans.findLibraryCard(item).isStocked() && retentions.notContains(item)) {
                return StockStatus.在庫あり;
            }
        }

        return StockStatus.在庫なし;
    }
}
