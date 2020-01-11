package library.domain.model.reserving;

import library.domain.type.date.Date;

/**
 * 貸出準備完了日
 */
public class LoanPreparationCompletionDate {
    Date value;

    public LoanPreparationCompletionDate(Date value) {
        this.value = value;
    }

    public Date value() {
        return value;
    }
}
