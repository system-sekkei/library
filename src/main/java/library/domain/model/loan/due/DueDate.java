package library.domain.model.loan.due;

import library.domain.model.loan.Loan;
import library.domain.model.delay.DaysPeriod;
import library.domain.model.loan.LoanDate;
import library.domain.type.date.CurrentDate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * 貸出期限日
 */
public class DueDate {
    LocalDate value;
    static final int 最大貸出日数 = 15;

    public DueDate(LocalDate value) {
        this.value = value;
    }

    public static DueDate 貸出期限日(Loan loan) {
        LocalDate loaned = loan.date().value();
        LocalDate 期限日 = loaned.plusDays(最大貸出日数).minusDays(1); // 借りた当日を含むため1日引いている
        return new DueDate(期限日);
    }

    DaysPeriod 遅延期間(CurrentDate 判定日) {
        long 月数 = value.until(判定日.value(), ChronoUnit.MONTHS);
        long 日数 = value.until(判定日.value(), ChronoUnit.DAYS);
        return DaysPeriod.遅延期間(月数, 日数);
    }

    public DueDateStatus status() {
        return null;
    }

}
