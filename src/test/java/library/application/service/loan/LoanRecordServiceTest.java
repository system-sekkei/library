package library.application.service.loan;

import library.LibraryDBTest;
import library.application.service.member.MemberQueryService;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.loan.Loan;
import library.domain.model.loan.LoanDate;
import library.domain.model.member.MemberNumber;
import library.domain.model.loan.LoanRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@LibraryDBTest
class LoanRecordServiceTest {
    @Autowired
    LoanRecordService loanRecordService;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    LoanQueryService loanQueryService;

    @Test
    void 貸出を登録できる() {
        ItemNumber itemNumber = new ItemNumber("2-A");
        LoanRequest loanRequest = new LoanRequest(new MemberNumber(1), itemNumber, LoanDate.parse("2020-02-20"));
        loanRecordService.loaned(loanRequest);

        Loan loan = loanQueryService.findBy(itemNumber);

        assertAll(
                () -> assertEquals(loan.member().number().value(), 1),
                () -> assertEquals(loan.date().toString(), "2020-02-20"));
    }
}