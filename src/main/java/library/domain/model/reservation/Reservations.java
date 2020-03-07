package library.domain.model.reservation;

import java.util.List;

/**
 * 貸出予約リスト
 */
public class Reservations {
    List<ReservedBook> list;

    public Reservations(List<ReservedBook> list) {
        this.list = list;
    }

    public NumberOfReservation numberOfReservation() {
        return new NumberOfReservation(list.size());
    }

    public List<ReservedBook> list() {
        return list;
    }
}
