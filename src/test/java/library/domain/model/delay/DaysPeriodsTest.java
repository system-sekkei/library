package library.domain.model.delay;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 遅延期間のリストのテスト
 */
class DaysPeriodsTest {

    // TODO 遅延期間　にmonths は不要では？
    //  DaysPeriod.遅延期間(1, 40) は40日の遅延?
    //  DaysPeriod.遅延期間(0, 40) は40日の遅延?
    @Test
    void 最大遅延日数を取得する() {
        DaysPeriods daysPeriods = new DaysPeriods(List.of(
                DaysPeriod.遅延期間(0, 3),
                DaysPeriod.遅延期間(0, 30),
                DaysPeriod.遅延期間(1, 31),
                DaysPeriod.遅延期間(2, 0)
        ));

        DaysPeriod 最大遅延日数 = daysPeriods.最大遅延日数();

        assertAll(
                () -> assertEquals(2, 最大遅延日数.months.value()),
                () -> assertEquals(0, 最大遅延日数.days.value())
        );
    }

}