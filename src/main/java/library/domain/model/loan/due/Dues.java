package library.domain.model.loan.due;

import library.domain.model.loan.Loans;
import library.domain.model.loan.delay.DaysLate;
import library.domain.model.loan.delay.DelayStatus;
import library.domain.type.date.CurrentDate;
import library.domain.type.date.Days;

import java.util.Comparator;

/**
 * 貸出期限のリスト
 */
public class Dues {
    Loans loans;

    public Dues(Loans loans) {
        this.loans = loans;
    }

    public DelayStatus worst(CurrentDate date) {
        DaysLate worstDays = worstDays(date);
        return worstDays.delayStatus();
    }

    DaysLate worstDays(CurrentDate date) {
        return loans.asList().stream()
                .map(loan -> DueDate.from(loan).daysLate(date))
                .max(Comparator.comparingInt(period -> period.intValue()))
                .orElse(new DaysLate(new Days(0)));
    }
}
