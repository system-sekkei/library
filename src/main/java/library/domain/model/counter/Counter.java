package library.domain.model.counter;

import library.domain.model.bookonloan.librarycard.LibraryCardShelf;
import library.domain.model.holding.CatalogShelf;
import library.domain.model.holding.HoldingStatus;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.Reservations;
import library.domain.model.reservation.reservation.ReservedBook;
import library.domain.model.retention.RetentionShelf;
import library.domain.model.retention.Retentionability;
import library.domain.model.retention.RetentionableReservedBooks;

import java.util.ArrayList;
import java.util.List;

/**
 * カウンター
 */
public class Counter {
    CatalogShelf catalogShelf;
    LibraryCardShelf libraryCardShelf;
    RetentionShelf retentionShelf;

    public Counter(CatalogShelf catalogShelf, LibraryCardShelf libraryCardShelf, RetentionShelf retentionShelf) {
        this.catalogShelf = catalogShelf;
        this.libraryCardShelf = libraryCardShelf;
        this.retentionShelf = retentionShelf;
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

    private Retentionability retentionability(Reservation reservation) {
        // TODO:
        return Retentionability.対象外;
    }

    private HoldingStatus checkHoldingStatus(ReservedBook reservedBook) {
        // TODO: 在庫チェック処理
        return HoldingStatus.在庫中;
    }
}
