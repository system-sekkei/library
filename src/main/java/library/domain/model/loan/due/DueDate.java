package library.domain.model.loan.due;

import library.domain.model.loan.LoanDate;
import library.domain.model.loan.delay.DaysLate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * 貸出期限
 */
public class DueDate {
    LocalDate value;
    static final int 最大貸出日数 = 14;

    DueDate(LocalDate value) {
        this.value = value;
    }

    public DaysLate daysLate(LocalDate 判定日) {
        long 日数 = value.until(判定日, ChronoUnit.DAYS);
        return DaysLate.from(日数);
    }

    public static DueDate from(LoanDate loanDate) {
        LocalDate loaned = loanDate.value();
        LocalDate 期限日 = loaned.plusDays(最大貸出日数);
        return new DueDate(期限日);
    }
}
