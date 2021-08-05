package library.domain.model.reservation.loanability;

import library.domain.model.material.bibliography.Book;
import library.domain.model.material.bibliography.BookNumber;

/**
 * 本の貸出可否
 */
public class BookLoanability {
    Book book;
    int loanableItems;

    public String showLoanability() {
        return loanability().show();
    }

    private Loanability loanability() {
        return Loanability.loanability(loanableItems);
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
        return "BookLoanability{" +
                "book=" + book +
                ", loanable=" + loanableItems +
                '}';
    }
}
