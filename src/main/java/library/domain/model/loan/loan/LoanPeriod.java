package library.domain.model.loan.loan;

import library.domain.type.date.Days;

import java.time.Period;

/**
 * 貸出期間
 */
public class LoanPeriod {

    Days value;

    public LoanPeriod(Days value) {
        this.value = value;
    }

    static Days standard() {
        return new Days(14);
    }

    public Days value() {
        return value;
    }
}
