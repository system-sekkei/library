package library.application.repository;

import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookonloan.BookOnLoan;
import library.domain.model.bookonloan.LoaningOfBookCollection;
import library.domain.model.bookonloan.MemberAllBookOnLoans;
import library.domain.model.bookonloan.ReturningBookOnLoan;
import library.domain.model.member.Member;

/**
 * 貸出図書リポジトリ
 */
public interface BookOnLoanRepository {

    void registerBookOnLoan(LoaningOfBookCollection loaningOfBookCollection);

    MemberAllBookOnLoans findMemberAllBookOnLoans(Member member);

    BookOnLoan findBookOnLoanByBookCollectionCode(BookCollectionCode bookCollectionCode);

    void registerReturnBook(ReturningBookOnLoan returningBookOnLoan);
}
