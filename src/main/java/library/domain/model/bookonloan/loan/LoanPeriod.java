package library.domain.model.bookonloan.loan;

import java.time.Period;

/**
 * 貸出期間
 */
public class LoanPeriod {

    Period value;

    public LoanPeriod(Period value) {
        this.value = value;
    }

    static LoanPeriod standard() {
        return new LoanPeriod(Period.ofDays(14));
    }

    public Period value() {
        return value;
    }
}
