package library.domain.model.retention;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 取置期限
 */
class ExpireDate {
    LocalDate value;

    private ExpireDate(LocalDate value) {
        this.value = value;
    }
    // TODO: 休館日を考慮
    static int 取置の最大日数 = 7;
    boolean isExpired() {
        LocalDate today = LocalDate.now();
        return value.isBefore(today);
    }

    public String show() {
        String date = value.format(DateTimeFormatter.ofPattern("M月d日(E)"));
        return String.format("%s%s", isExpired() ? "期限切れ:" : "" , date);
    }
    static ExpireDate of(RetainedDate date) {
        return new ExpireDate(date.value.plusDays(取置の最大日数));
    }
}
