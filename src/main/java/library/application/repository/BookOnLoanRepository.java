package library.application.repository;

import library.domain.model.bookonloan.BookOnLoan;
import library.domain.model.bookonloan.BookOnLoans;
import library.domain.model.member.MemberNumber;

/**
 * 貸出図書リポジトリ
 */
public interface BookOnLoanRepository {

    void registerBookOnLoan(BookOnLoan bookOnLoan);

    BookOnLoans findMemberAllBookOnLoans(MemberNumber memberNumber);
}
