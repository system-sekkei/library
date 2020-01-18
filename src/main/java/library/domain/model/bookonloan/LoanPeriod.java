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
        Date today = Date.now();
        if (value.isBefore(today)) {
            return LoanPeriodStatus.期限切れ;
        }
        return LoanPeriodStatus.期限内;
    }

    public Date value() {
        return value;
    }
}
