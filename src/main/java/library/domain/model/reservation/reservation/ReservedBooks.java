package library.domain.model.reservation.reservation;

import library.domain.model.retention.Retention;
import library.domain.model.retention.Retentionability;

import java.util.ArrayList;
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
