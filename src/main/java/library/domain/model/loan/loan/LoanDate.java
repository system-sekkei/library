package library.domain.model.loan.loan;

import library.domain.type.date.Date;
import library.domain.type.date.Days;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;

/**
 * 貸出日
 */
public class LoanDate {
    @Valid
    @NotNull(message = "貸出日を入力してください")
    Date value;

    @Deprecated
    LoanDate() {
    }

    public LoanDate(Date value) {
        this.value = value;
    }

    public Date value() {
        return value;
    }

    Date dueDate() {
        return value.plusDays(LoanPeriod.standard());
    }
    @Override
    public String toString() {
        return value.toString();
    }
}
