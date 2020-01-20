package library.application.repository;

import library.domain.model.bookonloan.BookOnLoan;
import library.domain.model.bookonloan.BookOnLoans;

/**
 * 貸出図書リポジトリ
 */
public interface BookOnLoanRepository {

    void registerBookOnLoan(BookOnLoan bookOnLoan);

    BookOnLoans findBookOnLoans();
}
