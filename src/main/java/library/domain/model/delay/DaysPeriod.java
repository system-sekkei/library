package library.domain.model.delay;

import library.domain.type.date.Days;
import library.domain.type.date.Months;

/**
 * 遅延期間
 */
public class DaysPeriod {
    Months months;
    Days days;

    public DaysPeriod(Months months, Days days) {
        this.months = months;
        this.days = days;
    }

    public int daysIntValue() {
        return days.value();
    }

    public int monthsIntValue() {
        return months.value();
    }

    public static DaysPeriod 遅延期間(long months, long days) {
        Months 遅延月数 = new Months((int) months);
        Days 遅延日数 = new Days((int) days);
        return new DaysPeriod(遅延月数, 遅延日数);
    }

    public int compareTo(DaysPeriod target) {
        if (this.days.value() == target.daysIntValue()) return 0;
        if (this.days.value() > target.daysIntValue()) return 1;

        return -1;
    }
}
