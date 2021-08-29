package library.domain.model.delay;

/**
 * 遅延状態
 */
public enum DelayStatus {
    遅延日数１５日未満,
    遅延日数２ヶ月未満,
    遅延日数２ヶ月以上;

    public static DelayStatus level(DaysPeriod 遅延期間) {
        if (遅延期間.days.未満(15) && 遅延期間.months.未満(1)) return 遅延日数１５日未満;
        if (遅延期間.days.以上(15) && 遅延期間.months.未満(2)) return 遅延日数２ヶ月未満;
        return 遅延日数２ヶ月以上;
    }
}
