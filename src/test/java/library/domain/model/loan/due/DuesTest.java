package library.domain.model.loan.due;

import library.domain.model.delay.DaysPeriod;
import library.domain.model.loan.*;
import library.domain.model.member.Member;
import library.domain.model.member.MemberType;
import library.domain.type.date.CurrentDate;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DuesTest {

    @Test
    void 遅延日数を出すことができる() {
        DueDate 返却予定日 = new DueDate(LocalDate.now().minusDays(15));
        LoanDate 十五日返却延滞している貸出の貸出日 = LoanDate.from(返却予定日);
        Loans loans = new Loans(List.of(
                new Loan(new LoanNumber(1),
                        new Member(null, null, MemberType.中学生以上),
                        null,
                        十五日返却延滞している貸出の貸出日)
        ));
        Dues dues = new Dues(loans);
        DaysPeriod 遅延日数 = dues.遅延日数(new CurrentDate(LocalDate.now())).最大遅延日数();

        assertAll(
                () -> assertEquals(0, 遅延日数.monthsIntValue()),
                () -> assertEquals(15, 遅延日数.daysIntValue())
        );
    }
}