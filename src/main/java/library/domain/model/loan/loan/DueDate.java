package library.domain.model.loan.loan;

import java.time.LocalDate;

/**
 * 貸出期限
 */
class DueDate {
    LocalDate value;

    DueDate(LocalDate value) {
        this.value = value;
    }

    public LocalDate value() {
        return value;
    }
}
