package library.application.repository;

import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookonloan.loan.BookOnLoan;
import library.domain.model.bookonloan.loaning.BookOnLoanRequest;
import library.domain.model.bookonloan.loaning.MemberAllBookOnLoans;
import library.domain.model.bookonloan.returning.ReturningBookOnLoan;
import library.domain.model.member.Member;

/**
 * 貸出図書リポジトリ
 */
public interface BookOnLoanRepository {

    BookOnLoan registerBookOnLoan(BookOnLoanRequest bookOnLoanRequest);

    MemberAllBookOnLoans findMemberAllBookOnLoans(Member member);

    BookOnLoan findBookOnLoanByBookCollectionCode(BookCollectionCode bookCollectionCode);

    void registerReturnBook(ReturningBookOnLoan returningBookOnLoan);
}
