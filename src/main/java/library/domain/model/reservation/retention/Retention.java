package library.domain.model.reservation.retention;

import library.domain.model.item.ItemNumber;
import library.domain.model.reservation.reservation.ReservationNumber;

import javax.validation.Valid;

public class Retention {
    ReservationNumber reservationNumber;
    @Valid
    ItemNumber itemNumber;

    public ReservationNumber reservationNumber() {
        return reservationNumber;
    }

    @Override
    public String toString() {
        return "Retention{" +
                "reservationNumber=" + reservationNumber +
                ", itemNumber=" + itemNumber +
                '}';
    }
}
