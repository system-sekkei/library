package library.domain.model.reservation.reservation;

import java.util.List;

/**
 * 貸出予約リスト
 */
public class ReservedBooks {
    List<ReservedBook> list;

    public ReservedBooks(List<ReservedBook> list) {
        this.list = list;
    }

    public NumberOfReservation numberOfReservation() {
        return new NumberOfReservation(list.size());
    }

    public List<ReservedBook> list() {
        return list;
    }

}
