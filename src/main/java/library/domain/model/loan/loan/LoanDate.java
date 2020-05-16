package library.domain.model.loan.loan;

import library.domain.type.date.Date;
import library.domain.type.date.Days;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;

/**
 * 貸出日
 */
public class LoanDate {
    @NotNull(message = "貸出日を入力してください")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate value;

    @Deprecated
    private LoanDate() {
    }

    private LoanDate(LocalDate value) {
        this.value = value;
    }

    DueDate dueDate() {
        LocalDate dueDate = value.plusDays(LoanPeriod.standard().value());
        return new DueDate(dueDate);
    }
    @Override
    public String toString() {
        return value.toString();
    }

    public static LoanDate now() {
        return new LoanDate(LocalDate.now());
    }

    public static LoanDate parse(String dateText) {
        return new LoanDate(LocalDate.parse(dateText));
    }
}
