package library.domain.model.reservation.loanability;

import library.domain.model.material.bibliography.NumberOfBook;

import java.util.List;

/**
 * 本の一覧と貸出可否
 */
public class BookLoanabilities {
    List<BookLoanability> list;

    public BookLoanabilities(List<BookLoanability> list) {
        this.list = list;
    }

    public NumberOfBook numberOfBook() {
        return new NumberOfBook(list.size());
    }
    public int size() {
        return list.size();
    }

    public List<BookLoanability> asList() {
        return list;
    }

    @Override
    public String toString() {
        return "BookLoanabilities{" +
                "list=" + list +
                '}';
    }
}
