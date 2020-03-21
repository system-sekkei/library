package library.domain.model.counter;


import library.domain.model.counter.Counters;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.Reservations;
import library.domain.model.retention.Retentionability;
import library.domain.model.retention.RetentionableReservedBooks;

import java.util.ArrayList;
import java.util.List;

/**
 * 取置
 */
public class Retention {
    List<Counters> list;

    public Retention(List<Counters> list) {
        this.list = list;
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
        for (Counters counters : list) {
            if (counters.sameBook(reservation.reservedBook())) {
                return counters.retentinability();
            }
        }
        return Retentionability.対象外;
    }
}
