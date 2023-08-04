package library.domain.model.loan.due;

import library.domain.model.delay.DaysPeriod;
import library.domain.model.loan.Loan;
import library.domain.model.loan.LoanDate;
import library.domain.type.date.CurrentDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 貸出期限日のテスト
 */
class DueDateTest {

    @Test
    void 貸出期限日を取得できる() {
        Loan loan = new Loan(null, null, null, LoanDate.from(LocalDate.of(2021, 8, 1)));
        DueDate actual = DueDate.貸出期限日(loan);
        assertEquals(LocalDate.of(2021, 8, 15), actual.value);
    }


    @Test
    @DisplayName("2023-05-03から2023-07-03の期間は月数は2月、日数は61")
    void 遅延日数を取得する() {
        DueDate sut = new DueDate(LocalDate.parse("2023-05-03"));
        DaysPeriod 遅延期間 = sut.遅延期間(CurrentDate.parse("2023-07-03"));
        assertAll(
                () -> assertEquals(2, 遅延期間.monthsIntValue()),
                () -> assertEquals(61, 遅延期間.daysIntValue())
        );
    }

}