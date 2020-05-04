package library.application.repository;

import library.domain.model.bookonloan.loan.BookOnLoan;
import library.domain.model.bookonloan.loaning.BookOnLoanRequest;
import library.domain.model.bookonloan.loaning.MemberAllBookOnLoans;
import library.domain.model.bookonloan.returning.ReturningBookOnLoan;
import library.domain.model.item.ItemNumber;
import library.domain.model.member.Member;

/**
 * 貸出図書リポジトリ
 */
public interface BookOnLoanRepository {

    BookOnLoan registerBookOnLoan(BookOnLoanRequest bookOnLoanRequest);

    MemberAllBookOnLoans findMemberAllBookOnLoans(Member member);

    BookOnLoan findBookOnLoanByItemNumber(ItemNumber itemNumber);

    void registerReturnBook(ReturningBookOnLoan returningBookOnLoan);
}
