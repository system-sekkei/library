package library.domain.model.reservation.reservation;

import library.domain.model.book.BookIds;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ReservedBook> asList() {
        return list;
    }

    public BookIds bookIds() {
        return new BookIds(list.stream()
            .map(reservedBook -> reservedBook.book().bookId())
            .collect(Collectors.toList()));
    }
}
