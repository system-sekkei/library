package library.domain.type.date;

import java.time.LocalDate;

/**
 * 現在日
 * （状態の時点を表現するクラス）
 */
public class CurrentDate {
    LocalDate value;

    public CurrentDate(LocalDate value) {
        this.value = value;
    }

    public LocalDate value() {
        return value;
    }

    public static CurrentDate parse(String dateText) {
        return new CurrentDate(LocalDate.parse(dateText));
    }
}
