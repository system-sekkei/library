package library.domain.model.loan.due;

import library.domain.model.loan.Loan;
import library.domain.model.loan.LoanDate;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DueDateTest {

    @Test
    void 貸出期限日を取得できる() {
        Loan loan = new Loan(null, null, null, LoanDate.from(LocalDate.of(2021, 8, 1)));
        DueDate actual = DueDate.from(loan);
        assertEquals(LocalDate.of(2021, 8, 15), actual.value);
    }
}