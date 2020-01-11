package library.domain.model.reserving;

import library.domain.type.date.Date;

/**
 * 取置期限
 */
public class RetentionDeadline {
    Date value;

    public RetentionDeadline(Date value) {
        this.value = value;
    }

    public static RetentionDeadline deadline(LoanPreparationCompletionDate date) {
        return new RetentionDeadline(date.value().plusDays(7));
    }
}
