package library.domain.model.reservation.retention;

import library.domain.model.item.ItemNumber;
import library.domain.model.reservation.reservation.ReservationNumber;

import javax.validation.Valid;

public class Retention {
    ReservationNumber reservationNumber;
    @Valid
    ItemNumber itemNumber = ItemNumber.empty();

    public ReservationNumber reservationNumber() {
        return reservationNumber;
    }

    public ItemNumber itemNumber() {
        return itemNumber;
    }

    @Override
    public String toString() {
        return "Retention{" +
                "reservationNumber=" + reservationNumber +
                ", itemNumber=" + itemNumber +
                '}';
    }
}
