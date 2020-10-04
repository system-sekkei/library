package library.domain.model.loan.delay;

import library.domain.type.date.Days;

/**
 * 遅延状態
 */
public enum DelayStatus {
    遅延日数合計３日未満,
    遅延日数合計７日未満,
    それ以外;

    public static DelayStatus level(Days days) {
        if (days.lessThan(3)) return 遅延日数合計３日未満;
        if (days.lessThan(7)) return 遅延日数合計７日未満;
        return それ以外;
    }
}
