package library.domain.model.loan;

import library.domain.model.loan.due.DueDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 貸出日
 */
public class LoanDate {
    @NotNull(message = "貸出日を入力してください")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate value;

    @Deprecated
    LoanDate() {
    }

    public LoanDate(LocalDate value) {
        this.value = value;
    }

    public static LoanDate from(DueDate 返却予定日) {
        return 返却予定日.貸出日();
    }

    public String show() {
        return value.format(DateTimeFormatter.ofPattern("M月d日(E)"));
    }
    @Override
    public String toString() {
        return value.toString();
    }

    public static LoanDate now() {
        return new LoanDate(LocalDate.now());
    }

    public static LoanDate from(LocalDate localDate) {
        return new LoanDate(localDate);
    }
    public static LoanDate parse(String dateText) {
        return new LoanDate(LocalDate.parse(dateText));
    }

    public LocalDate value() {
        return value;
    }

    public LoanDate minusDays(int days) {
        return new LoanDate(this.value().minusDays(days));
    }

    public boolean sameValue(LoanDate other) {
        return value.equals(other.value);
    }
}
