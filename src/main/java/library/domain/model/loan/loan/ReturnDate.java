package library.domain.model.loan.loan;

import library.domain.type.date.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 返却日
 */
public class ReturnDate {
    @Valid
    @NotNull(message = "返却日を入力してください")
    Date value;

    @Deprecated
    ReturnDate() {
    }

    public ReturnDate(Date value) {
        this.value = value;
    }

    public Date value() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
