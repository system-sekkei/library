package library.domain.model.reservation.reservation;

import library.domain.model.book.bibliography.BookNumbers;

import java.util.List;
import java.util.stream.Collectors;

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

    public BookNumbers bookIds() {
        return new BookNumbers(list.stream()
            .map(reservedBook -> reservedBook.reservedBook().book().bookNumber())
            .collect(Collectors.toList()));
    }
}
