package library.domain.model.retention;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 取置期限のテスト
 */
class ExpireDateTest {

    @Disabled("リフレクションなしではテストできない")
    @Test
    void isExpired() {
        LocalDate now = LocalDate.now();
        ExpireDate sut = ExpireDate.of(RetainedDate.now());
        fail();
    }

}