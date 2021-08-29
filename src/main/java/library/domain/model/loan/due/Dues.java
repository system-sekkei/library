package library.domain.model.loan.due;

import library.domain.model.loan.Loans;
import library.domain.model.delay.DaysPeriod;
import library.domain.model.delay.DaysPeriods;
import library.domain.model.delay.DelayStatus;
import library.domain.type.date.CurrentDate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 貸出期限のリスト
 */
public class Dues {
    Loans loans;

    public Dues(Loans loans) {
        this.loans = loans;
    }

    public DelayStatus 遅延状態(CurrentDate 現在日) {
        return 遅延日数(現在日).遅延状態();
    }

    DaysPeriods 遅延日数(CurrentDate date) {
        List<DaysPeriod> lists = loans.asList().stream()
                .map(loan -> DueDate.貸出期限(loan).遅延期間(date)).collect(Collectors.toList());

        return new DaysPeriods(lists);
    }
}
