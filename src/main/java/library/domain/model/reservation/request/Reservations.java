package library.domain.model.reservation.request;

import java.util.List;

/**
 * 貸出予約リスト
 */
public class Reservations {
    List<Reservation> list;

    public Reservations(List<Reservation> list) {
        this.list = list;
    }

    public NumberOfReservation numberOfReservation() {
        return new NumberOfReservation(list.size());
    }

    public List<Reservation> asList() {
        return list;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
    @Override
    public String toString() {
        return "Reservations{" +
                "list=" + list +
                '}';
    }
}
