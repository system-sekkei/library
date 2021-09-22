package library.domain.model.delay;

import java.util.List;

/**
 * 遅延期間のリスト
 */
public class DaysPeriods {
    List<DaysPeriod> lists;

    public DaysPeriods(List<DaysPeriod> lists) {
        this.lists = lists;
    }

    public DelayStatus 遅延状態() {
        DaysPeriod 最大遅延日数 = 最大遅延日数();
        return DelayStatus.level(最大遅延日数);
    }

    public DaysPeriod 最大遅延日数() {
        DaysPeriod 遅延０日 = DaysPeriod.遅延期間(0, 0);
        return lists.stream().max(DaysPeriod::compareTo).orElse(遅延０日);
    }

}
