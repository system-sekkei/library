package library.infrastructure.datasource.loan;

import library.domain.model.loan.LoanRequest;

public class RegisterLoanException extends RuntimeException {
    LoanRequest loanRequest;

    public RegisterLoanException(LoanRequest loanRequest) {
        super("貸出中の所蔵品に対して貸出登録が行われました。");
        this.loanRequest = loanRequest;
    }
}
