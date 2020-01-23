package library.application.repository;

import library.domain.model.bookonloan.BookOnLoanRegister;
import library.domain.model.bookonloan.BookOnLoans;

/**
 * 貸出図書リポジトリ
 */
public interface BookOnLoanRepository {

    void registerBookOnLoan(BookOnLoanRegister bookOnLoanRegister);

    BookOnLoans findBookOnLoans();
}
