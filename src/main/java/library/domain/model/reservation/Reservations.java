package library.domain.model.reservation;

import java.util.List;

/**
 * 貸出予約リスト
 */
public class Reservations {
    List<Reservation> list;

    public Reservations(List<Reservation> list) {
        this.list = list;
    }
}
