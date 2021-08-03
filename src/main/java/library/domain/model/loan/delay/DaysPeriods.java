package library.domain.model.loan.delay;

import library.domain.type.date.Days;
import library.domain.type.date.Months;

import java.util.List;
import java.util.Optional;

/**
 * 遅延期間のリスト
 */
public class DaysPeriods {
    List<DaysPeriod> lists;

    public DaysPeriods(List<DaysPeriod> lists) {
        this.lists = lists;
    }

    public DelayStatus 遅延状態() {
        Optional<DaysPeriod> 最大遅延日数 = lists.stream().max(DaysPeriod::compareTo);

        DaysPeriod 遅延０日 = new DaysPeriod(new Months(0), new Days(0));

        return DelayStatus.level(最大遅延日数.orElse(遅延０日));
    }
}
