package library.domain.model.loan;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 貸出日のテスト
 */
class LoanDateTest {

    @Test
    void 貸出日の表示文字列の取得() {
        LoanDate sut = new LoanDate(LocalDate.parse("2023-07-01"));

        String show = sut.show();

        assertEquals("7月1日(土)", show);
    }
}