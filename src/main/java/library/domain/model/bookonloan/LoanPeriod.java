package library.domain.model.bookonloan;

import library.domain.type.date.Date;

/**
 * 貸出期限
 */
public class LoanPeriod {
    Date value;

    public LoanPeriod(Date value) {
        this.value = value;
    }

    public static LoanPeriod loanPeriod(LoanDate loanDate) {
        return new LoanPeriod(loanDate.value().plusDays(14));
    }

    public LoanPeriodStatus loanPeriodStatus() {
        return null;
    }
}