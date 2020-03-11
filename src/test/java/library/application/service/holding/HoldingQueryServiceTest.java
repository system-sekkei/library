package library.application.service.holding;

import library.LibraryDBTest;
import library.domain.model.holding.HoldingCode;
import library.domain.model.holding.HoldingOnLoan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@LibraryDBTest
class HoldingQueryServiceTest {

    @Autowired
    HoldingQueryService holdingQueryService;

    @Test
    void 貸出中の蔵書を取得できる() {
        HoldingCode holdingCode = new HoldingCode("1-A");
        HoldingOnLoan holdingOnLoan = holdingQueryService.findHoldingOnLoan(holdingCode);

        assertTrue(holdingOnLoan.holding().holdingCode().sameValue(holdingCode));
    }
}