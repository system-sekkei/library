package library.domain.model.reservation.retention;

import java.time.LocalDate;

/**
 * 取置日
 * RDRAのバリエーション図では貸出準備完了日
 */
public class RetainedDate {
    LocalDate value;

    public RetainedDate(LocalDate value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public static RetainedDate now() {
        return new RetainedDate(LocalDate.now());
    }
}
