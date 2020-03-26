package library.domain.model.retention;

import library.domain.model.reservation.reservation.NumberOfReservation;
import library.domain.model.reservation.reservation.Reservation;

import java.util.List;

/**
 * 取置可能な貸出予約
 */
public class RetentionableReservations {
    List<Reservation> list;

    public RetentionableReservations(List<Reservation> list) {
        this.list = list;
    }

    public NumberOfReservation numberOfReservation() {
        return new NumberOfReservation(list.size());
    }

    public List<Reservation> asList() {
        return list;
    }

}
