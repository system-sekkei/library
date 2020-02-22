package library.application.coordinator.bookonloan;

import library.domain.model.bookonloan.loaning.CanLoan;

/**
 * 貸出図書 登録結果
 */
public enum LoaningResult {
    貸出可能("", CanLoan.貸出可能),
    貸出中の蔵書("現在貸出中の蔵書です。", null),
    貸出制限エラー("これ以上本を貸し出すことができません。", CanLoan.貸出不可);

    String message;
    CanLoan canLoan;

    LoaningResult(String message, CanLoan canLoan) {
        this.message = message;
        this.canLoan = canLoan;
    }

    static public LoaningResult bookOnLoanValidResult(CanLoan canLoan) {
        for (LoaningResult value : values()) {
            if (value.canLoan == canLoan) {
                return value;
            }
        }

        throw new IllegalArgumentException("存在しないEnum値");
    }

    public String message() {
        return message;
    }

    public boolean hasError() {
        return this != 貸出可能;
    }
}
