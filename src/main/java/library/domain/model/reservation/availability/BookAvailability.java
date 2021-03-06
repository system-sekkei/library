package library.domain.model.reservation.availability;

import library.domain.model.book.bibliography.Book;
import library.domain.model.book.bibliography.BookNumber;

import static library.domain.model.reservation.availability.Availability.予約できる;

/**
 * 本の貸出可否
 */
public class BookAvailability {
    Book book;
    int loanableItems;

    public String showAvailability() {
        return availability().show();
    }

    public boolean isAvailable() {
        return 予約できる == availability();
    }

    private Availability availability() {
        return Availability.availability(loanableItems);
    }

    public String describeBook() {
        return book.show();
    }

    public BookNumber bookNumber() {
        return book.bookNumber();
    }

    // TODO テスト用：テストを変更して、このメソッドを廃止する
    public Book book() {
        return book;
    }

    @Override
    public String toString() {
        return "BookAvailability{" +
                "book=" + book +
                ", loanable=" + loanableItems +
                '}';
    }
}
