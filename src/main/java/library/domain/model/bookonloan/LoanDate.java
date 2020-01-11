package library.domain.model.bookonloan;

import library.domain.type.date.Date;

/**
 * 貸出日
 */
public class LoanDate {
    Date value;

    public LoanDate(Date value) {
        this.value = value;
    }

    public Date value() {
        return value;
    }
}
