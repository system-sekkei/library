package library.domain.model;

import library.domain.type.date.Date;

/**
 * 貸出準備完了日
 */
public class LoanPreparationCompletionDate {
    Date value;

    public LoanPreparationCompletionDate(Date value) {
        this.value = value;
    }

    public RetentionDeadline deadline() {
        return new RetentionDeadline(value.plusDays(7));
    }
}
