package library.domain.model.delay;

import library.domain.type.date.Days;
import library.domain.type.date.Months;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 遅延状態のテスト
 */
class DelayStatusTest {

    // TODO 遅延していない場合の区分がない
    @Test
    void _14日の遅延は15日未満() {
        DelayStatus level = DelayStatus.level(new DaysPeriod(new Months(0), new Days(14)));
        assertEquals(DelayStatus.遅延日数１５日未満, level);
    }

    @Test
    void _60日の遅延は2か月未満() {
        DelayStatus level = DelayStatus.level(new DaysPeriod(new Months(1), new Days(60)));
        assertEquals(DelayStatus.遅延日数２ヶ月未満, level);
    }

    @Test
    void _70日の遅延は2ヶ月以上() {
        DelayStatus level = DelayStatus.level(new DaysPeriod(new Months(2), new Days(70)));
        assertEquals(DelayStatus.遅延日数２ヶ月以上, level);
    }

    @Test
    void _34日の遅延は2か月未満() {
        DelayStatus level = DelayStatus.level(new DaysPeriod(new Months(1), new Days(34)));
        assertEquals(DelayStatus.遅延日数２ヶ月未満, level);
    }
}