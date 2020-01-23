package library.application.repository;

import library.domain.model.bookonloan.BookOnLoanRegister;
import library.domain.model.bookonloan.BookOnLoans;
import library.domain.model.member.MemberNumber;

/**
 * 貸出図書リポジトリ
 */
public interface BookOnLoanRepository {

    void registerBookOnLoan(BookOnLoanRegister bookOnLoanRegister);

    BookOnLoans findMemberAllBookOnLoans(MemberNumber memberNumber);
}
