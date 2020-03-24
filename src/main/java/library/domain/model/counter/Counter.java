package library.domain.model.counter;

import library.domain.model.bookonloan.librarycard.LibraryCardShelf;
import library.domain.model.holding.Catalog;
import library.domain.model.holding.Holding;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.Reservations;
import library.domain.model.reservation.reservation.ReservedBook;
import library.domain.model.retention.RetentionShelf;
import library.domain.model.retention.RetentionableReservedBooks;

import java.util.ArrayList;
import java.util.List;

/**
 * カウンター
 */
public class Counter {
    Catalog catalog;
    LibraryCardShelf libraryCardShelf;
    RetentionShelf retentionShelf;

    public Counter(Catalog catalog, LibraryCardShelf libraryCardShelf, RetentionShelf retentionShelf) {
        this.catalog = catalog;
        this.libraryCardShelf = libraryCardShelf;
        this.retentionShelf = retentionShelf;
    }

    public RetentionableReservedBooks retentionableReservedBooks(Reservations reservations) {
        List<Reservation> list = new ArrayList<>();
        for (Reservation reservation : reservations.asList()) {
            if (stockStatus(reservation.reservedBook()) == StockStatus.在庫あり) {
                list.add(reservation);
            }
        }

        return new RetentionableReservedBooks(list);
    }

    private StockStatus stockStatus(ReservedBook reservedBook) {
        Catalog sameBookHoldings = catalog.findHoldingsByBook(reservedBook.book());

        for (Holding holding : sameBookHoldings.list()) {
            if (libraryCardShelf.findLibraryCard(holding).isStocked() && retentionShelf.notContains(holding)) {
                return StockStatus.在庫あり;
            }
        }

        return StockStatus.在庫なし;
    }
}
