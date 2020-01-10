package library.domain.model;

import library.domain.type.date.Date;

/**
 * 貸出日
 */
public class LoanDate {
    Date value;

    public LoanDate(Date value) {
        this.value = value;
    }

    public LoanPeriod loanPeriod() {
        return new LoanPeriod(value.plusDays(14));
    }
}
