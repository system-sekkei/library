package library.application.repository;

import library.domain.model.item.ItemNumber;
import library.domain.model.loan.loan.Loan;
import library.domain.model.loan.returned.Returned;
import library.domain.model.loan.rule.LoanStatus;
import library.domain.model.member.MemberNumber;
import library.domain.model.loan.loan.LoanRequest;

/**
 * 貸出図書リポジトリ
 */
public interface LoanRepository {

    void loan(LoanRequest loanRequest);

    LoanStatus status(MemberNumber memberNumber);

    Loan findBy(ItemNumber itemNumber);

    void returned(Returned returned);
}
