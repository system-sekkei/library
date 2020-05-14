package library.infrastructure.datasource.loan;

import library.domain.model.loan.rule.LoanRequest;

public class RegisterLoanException extends RuntimeException {
    LoanRequest loanRequest;

    public RegisterLoanException(LoanRequest loanRequest) {
        super("貸出中の蔵書に対して貸出図書登録が行われました。");
        this.loanRequest = loanRequest;
    }
}
