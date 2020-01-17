package library.domain.model.bookonloan;

import library.domain.type.date.Date;

import java.time.Period;

/**
 * 遅延日数
 */
public enum DelayPeriod {
    遅延日数３日未満,
    遅延日数７日未満,
    それ以外;

    public static DelayPeriod from(LoanPeriod loanPeriod) {
        Date today = Date.now();
        int delay = Period.between(loanPeriod.value().value(), today.value()).getDays();

        if (delay < 3) {
            return 遅延日数３日未満;
        }

        if (delay < 7) {
            return 遅延日数７日未満;
        }

        return それ以外;
    }
}
