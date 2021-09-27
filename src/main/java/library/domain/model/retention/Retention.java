package library.domain.model.retention;

import library.domain.model.material.item.ItemNumber;
import library.domain.model.reservation.request.ReservationNumber;

import javax.validation.Valid;

/**
 * 取置依頼
 */
public class Retention {
    ReservationNumber reservationNumber;
    @Valid
    ItemNumber itemNumber = ItemNumber.empty();

    public Retention(@Valid ReservationNumber reservationNumber, @Valid ItemNumber itemNumber) {
        this.reservationNumber = reservationNumber;
        this.itemNumber = itemNumber;
    }

    @Deprecated
    Retention() {
    }

    public static Retention empty() {
        return new Retention();
    }

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
