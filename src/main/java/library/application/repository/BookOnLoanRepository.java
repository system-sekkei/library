package library.application.repository;

import library.domain.model.bookonloan.BookOnLoan;
import library.domain.model.bookonloan.MemberAllBookOnLoans;
import library.domain.model.member.Member;

/**
 * 貸出図書リポジトリ
 */
public interface BookOnLoanRepository {

    void registerBookOnLoan(BookOnLoan bookOnLoan);

    void registerReturnBook(BookOnLoan bookOnLoan);

    MemberAllBookOnLoans findMemberAllBookOnLoans(Member member);
}
