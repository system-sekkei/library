package library.domain.model.loan.loan;

import library.domain.type.date.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

    @Override
    public String toString() {
        return value.toString();
    }

    public DueDate dueDateWith(LoanPeriod loanPeriod) {
        return new DueDate(value.plus(loanPeriod.value()));
    }
}
