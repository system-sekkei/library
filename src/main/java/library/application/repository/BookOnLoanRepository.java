package library.application.repository;

import library.domain.model.loan.loan.BookOnLoan;
import library.domain.model.loan.rule.BookOnLoanRequest;
import library.domain.model.loan.rule.MemberAllBookOnLoans;
import library.domain.model.loan.loan.ReturningBookOnLoan;
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
