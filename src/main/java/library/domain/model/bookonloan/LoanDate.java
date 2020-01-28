package library.domain.model.bookonloan;

import library.domain.type.date.Date;

/**
 * 貸出日
 */
public class LoanDate {
    Date value;

    @Deprecated
    LoanDate() {
    }

    public LoanDate(Date value) {
        this.value = value;
    }

    public Date value() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
