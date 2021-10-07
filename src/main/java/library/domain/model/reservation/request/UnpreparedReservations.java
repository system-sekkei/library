package library.domain.model.reservation.request;

import library.domain.model.material.entry.EntryType;

import java.util.List;

/**
 * 未準備の貸出予約リスト
 */
public class UnpreparedReservations {
    List<UnpreparedReservation> list;

    public UnpreparedReservations(List<UnpreparedReservation> list) {
        this.list = list;
    }

    public NumberOfReservation numberOfReservation() {
        return new NumberOfReservation(list.size());
    }

    public List<UnpreparedReservation> asList() {
        return list;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
    @Override
    public String toString() {
        return "UnpreparedReservation{" +
                "list=" + list +
                '}';
    }

    public NumberOfReservation 視聴覚資料の冊数() {
        return new NumberOfReservation((int) list.stream().filter(reservation -> reservation.entry().所蔵品目種別() == EntryType.視聴覚資料).count());
    }
}
