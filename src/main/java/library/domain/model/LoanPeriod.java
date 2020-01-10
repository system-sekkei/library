package library.domain.model;

import library.domain.type.date.Date;

/**
 * 貸出期限
 */
public class LoanPeriod {
    Date value;

    public LoanPeriod(Date value) {
        this.value = value;
    }
}
