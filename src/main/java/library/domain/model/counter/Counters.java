package library.domain.model.counter;

import library.domain.model.book.BookId;
import library.domain.model.holding.HoldingStatus;
import library.domain.model.reservation.reservation.ReservedBook;
import library.domain.model.retention.Retentionability;

import java.util.List;

/**
 * カウンターのリスト
 */
public class Counters {
    BookId bookId;
    List<Counter> list;

    public Counters(BookId bookId, List<Counter> list) {
        this.bookId = bookId;
        this.list = list;
    }

    public boolean sameBook(ReservedBook reservedBook) {
        return reservedBook.isA(bookId);
    }

    public Retentionability retentinability() {
        for (Counter counter : list) {
            if (counter.holdingStatus() == HoldingStatus.在庫中) {
                return Retentionability.対象;
            }
        }
        return Retentionability.対象外;
    }
}
