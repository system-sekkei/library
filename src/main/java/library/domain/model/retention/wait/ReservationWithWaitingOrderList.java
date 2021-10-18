package library.domain.model.retention.wait;

import library.domain.model.material.entry.EntryType;
import library.domain.model.reservation.NumberOfReservation;

import java.util.List;

/**
 * 待ち順番と貸出予約リスト
 */
public class ReservationWithWaitingOrderList {
    List<ReservationWithWaitingOrder> list;

    public ReservationWithWaitingOrderList(List<ReservationWithWaitingOrder> list) {
        this.list = list;
    }

    public NumberOfReservation numberOfReservation() {
        return new NumberOfReservation(list.size());
    }

    public List<ReservationWithWaitingOrder> asList() {
        return list;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return "ReservationWithWaitingOrderList{" +
                "list=" + list +
                '}';
    }

    public NumberOfReservation 視聴覚資料の冊数() {
        return new NumberOfReservation((int) list.stream().filter(reservation -> reservation.entry().所蔵品目種別() == EntryType.視聴覚資料).count());
    }
}
