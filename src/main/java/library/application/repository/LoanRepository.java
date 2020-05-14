package library.application.repository;

import library.domain.model.loan.loan.Loan;
import library.domain.model.loan.rule.LoanRequest;
import library.domain.model.loan.rule.MemberAllBookOnLoans;
import library.domain.model.loan.loan.ReturningBookOnLoan;
import library.domain.model.book.item.ItemNumber;
import library.domain.model.member.Member;

/**
 * 貸出図書リポジトリ
 */
public interface LoanRepository {

    Loan registerLoan(LoanRequest loanRequest);

    MemberAllBookOnLoans findMemberAllBookOnLoans(Member member);

    Loan findLoanByItemNumber(ItemNumber itemNumber);

    void registerReturnBook(ReturningBookOnLoan returningBookOnLoan);
}
