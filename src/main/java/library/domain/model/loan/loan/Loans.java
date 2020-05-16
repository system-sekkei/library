package library.domain.model.loan.loan;

import library.domain.type.date.CurrentDate;
import library.domain.type.date.Days;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 貸出のリスト
 */
public class Loans {
    List<Loan> list;

    public Loans(List<Loan> list) {
        this.list = list;
    }

    public DelayStatus worst(CurrentDate date) {
        DaysLate worstDays = worstDays(date);
        return worstDays.delayStatus();
    }

    DaysLate worstDays(CurrentDate date) {
        return list.stream()
                .map(loan -> loan.daysLate(date))
                .max(Comparator.comparingInt(period -> period.intValue()))
                .orElse(new DaysLate(new Days(0)));
    }

    public int count() {
        return list.size();
    }

    public List<Loan> asList() {
        return Collections.unmodifiableList(list);
    }
}
