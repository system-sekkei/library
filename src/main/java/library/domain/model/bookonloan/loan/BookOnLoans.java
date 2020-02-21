package library.domain.model.bookonloan.loan;

import library.domain.model.bookonloan.loaning.MemberAllBookOnLoans;
import library.domain.type.date.Date;
import library.domain.type.date.Days;

import java.util.Comparator;
import java.util.List;

/**
 * 貸出図書リスト
 */
public class BookOnLoans {
    List<BookOnLoan> list;

    public BookOnLoans(List<BookOnLoan> list) {
        this.list = list;
    }

    public List<BookOnLoan> list() {
        return list;
    }

    public DelayPeriod worstDelayPeriod(Date date) {
        return list().stream()
                .map(loan -> loan.delayPeriod(date))
                .max(Comparator.comparingInt(period -> period.value.value()))
                .orElse(new DelayPeriod(new Days(0)));
    }
}
