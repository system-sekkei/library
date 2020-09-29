package library.domain.model.loan.due;

import library.domain.model.loan.Loans;
import library.domain.model.loan.delay.DaysLate;
import library.domain.model.loan.delay.DelayStatus;
import library.domain.type.date.CurrentDate;
import library.domain.type.date.Days;

/**
 * 貸出期限のリスト
 */
public class Dues {
    Loans loans;

    public Dues(Loans loans) {
        this.loans = loans;
    }

    public DelayStatus delayStatus(CurrentDate date) {
        return daysLate(date).delayStatus();
    }

    DaysLate daysLate(CurrentDate date) {
        int days = loans.asList().stream()
                .mapToInt(loan -> DueDate.from(loan).daysLate(date).intValue())
                .sum();
        return new DaysLate(new Days(days));
    }
}
