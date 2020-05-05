package library.infrastructure.datasource.bookonloan;

import library.domain.model.loan.rule.BookOnLoanRequest;

public class RegisterBookOnLoanException extends RuntimeException {
    BookOnLoanRequest bookOnLoanRequest;

    public RegisterBookOnLoanException(BookOnLoanRequest bookOnLoanRequest) {
        super("貸出中の蔵書に対して貸出図書登録が行われました。");
        this.bookOnLoanRequest = bookOnLoanRequest;
    }
}
