package library.application.repository;

import library.domain.model.loan.loan.Loan;
import library.domain.model.loan.rule.LoanStatus;
import library.domain.model.loan.loan.LoanRequest;
import library.domain.model.loan.returned.Returned;
import library.domain.model.item.ItemNumber;
import library.domain.model.member.Member;

/**
 * 貸出図書リポジトリ
 */
public interface LoanRepository {

    void registerLoan(LoanRequest loanRequest);

    LoanStatus loanStatus(Member member);

    Loan findLoanByItemNumber(ItemNumber itemNumber);

    void registerReturnBook(Returned returned);
}
