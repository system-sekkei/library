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

        DelayStatus delayStatus1 = DelayStatus.from(loanPeriod1);
        DelayStatus delayStatus2 = DelayStatus.from(loanPeriod2);
        DelayStatus delayStatus3 = DelayStatus.from(loanPeriod3);
        DelayStatus delayStatus4 = DelayStatus.from(loanPeriod4);
        DelayStatus delayStatus5 = DelayStatus.from(loanPeriod5);

        assertEquals(delayStatus1, DelayStatus.遅延日数３日未満);
        assertEquals(delayStatus2, DelayStatus.遅延日数３日未満);
        assertEquals(delayStatus3, DelayStatus.遅延日数７日未満);
        assertEquals(delayStatus4, DelayStatus.遅延日数７日未満);
        assertEquals(delayStatus5, DelayStatus.それ以外);
    }
}