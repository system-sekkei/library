package library.domain.model.loan.due;

import library.domain.model.loan.Loan;
import library.domain.model.loan.delay.DaysLate;
import library.domain.type.date.CurrentDate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * 貸出期限
 */
public class DueDate {
    LocalDate value;
    static final int 最大貸出日数 = 15;

    DueDate(LocalDate value) {
        this.value = value;
    }

    public static DueDate from(Loan loan) {
        LocalDate loaned = loan.date().value();
        LocalDate 期限日 = loaned.plusDays(最大貸出日数).minusDays(1);
        return new DueDate(期限日);
    }

    DaysLate daysLate(CurrentDate 判定日) {
        long 日数 = value.until(判定日.value(), ChronoUnit.DAYS);
        return DaysLate.from(日数);
    }

    public DueDateStatus status() {
        return null;
    }
}
