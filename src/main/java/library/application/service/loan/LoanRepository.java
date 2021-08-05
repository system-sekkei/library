package library.application.service.loan;

import library.domain.model.material.collection.ItemNumber;
import library.domain.model.loan.Loan;
import library.domain.model.returned.Returned;
import library.domain.model.loan.rule.LoanStatus;
import library.domain.model.member.MemberNumber;
import library.domain.model.loan.LoanRequest;

/**
 * 貸出リポジトリ
 */
public interface LoanRepository {

    void loan(LoanRequest loanRequest);

    LoanStatus status(MemberNumber memberNumber);

    Loan findBy(ItemNumber itemNumber);

    void returned(Returned returned);
}
