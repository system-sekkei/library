package library.domain.model.bookonloan;

import library.domain.type.date.Date;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DelayPeriodTest {

    @Test
    void 遅延日数の状態を生成することができる() {
        LoanPeriod loanPeriod1 = new LoanPeriod(Date.from("2020-01-18"));
        LoanPeriod loanPeriod2 = new LoanPeriod(Date.from("2020-01-15"));
        LoanPeriod loanPeriod3 = new LoanPeriod(Date.from("2020-01-14"));
        LoanPeriod loanPeriod4 = new LoanPeriod(Date.from("2020-01-11"));
        LoanPeriod loanPeriod5 = new LoanPeriod(Date.from("2020-01-10"));

        DelayPeriod delayPeriod1 = DelayPeriod.from(loanPeriod1);
        DelayPeriod delayPeriod2 = DelayPeriod.from(loanPeriod2);
        DelayPeriod delayPeriod3 = DelayPeriod.from(loanPeriod3);
        DelayPeriod delayPeriod4 = DelayPeriod.from(loanPeriod4);
        DelayPeriod delayPeriod5 = DelayPeriod.from(loanPeriod5);

        assertEquals(delayPeriod1, DelayPeriod.遅延日数３日未満);
        assertEquals(delayPeriod2, DelayPeriod.遅延日数３日未満);
        assertEquals(delayPeriod3, DelayPeriod.遅延日数７日未満);
        assertEquals(delayPeriod4, DelayPeriod.遅延日数７日未満);
        assertEquals(delayPeriod5, DelayPeriod.それ以外);
    }
}