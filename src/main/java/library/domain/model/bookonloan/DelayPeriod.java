package library.domain.model.bookonloan;

import library.domain.type.date.Days;

/**
 * 遅延日数
 */
public class DelayPeriod {
    Days value;

    public DelayPeriod(Days value) {
        this.value = value;
    }

    public DelayStatus delayStatus() {
        int delay = value.value();

        if (delay < 3) {
            return DelayStatus.遅延日数３日未満;
        }

        if (delay < 7) {
            return DelayStatus.遅延日数７日未満;
        }

        return DelayStatus.それ以外;
    }
}
