package library.application.repository;

import library.domain.model.bookonloan.BookOnLoanRegister;
import library.domain.model.bookonloan.MemberAllBookOnLoans;
import library.domain.model.member.MemberNumber;

/**
 * 貸出図書リポジトリ
 */
public interface BookOnLoanRepository {

    void registerBookOnLoan(BookOnLoanRegister bookOnLoanRegister);

    MemberAllBookOnLoans findMemberAllBookOnLoans(MemberNumber memberNumber);
}
