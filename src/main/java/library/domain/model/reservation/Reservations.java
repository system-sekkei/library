package library.domain.model.reservation;

import library.domain.model.material.entry.EntryType;
import library.domain.model.reservation.NumberOfReservation;
import library.domain.model.reservation.Reservation;

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

    public NumberOfReservation 視聴覚資料の冊数() {
        return new NumberOfReservation((int) list.stream().filter(reservation -> reservation.entry().所蔵品目種別() == EntryType.視聴覚資料).count());
    }
}
