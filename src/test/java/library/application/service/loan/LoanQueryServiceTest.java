package library.application.service.loan;

import library.LibraryDBTest;
import library.application.service.member.MemberQueryService;
import library.application.service.returns.ReturnBookRecordService;
import library.domain.model.item.ItemNumber;
import library.domain.model.loan.Loan;
import library.domain.model.loan.LoanDate;
import library.domain.model.returned.ReturnDate;
import library.domain.model.returned.Returned;
import library.domain.model.loan.rule.LoanStatus;
import library.domain.model.member.MemberNumber;
import library.domain.model.loan.LoanRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@LibraryDBTest
class LoanQueryServiceTest {
    @Autowired
    LoanQueryService loanQueryService;

    @Autowired
    ReturnBookRecordService returnBookRecordService;

    @Autowired
    LoanRecordService loanRecordService;

    @Autowired
    MemberQueryService memberQueryService;

    @Test
    void 貸出を取得できる() {
        registerLoan(new ItemNumber("2-A"), 1);

        Loan loan = loanQueryService.findBy(new ItemNumber("2-A"));

        assertEquals(loan.member().number().value(), 1);
    }

    @Test
    void 返却された貸出は取得できない() {
        ItemNumber itemNumber = new ItemNumber("2-B");
        registerLoan(itemNumber, 1);
        returnBookRecordService.returned(new Returned(itemNumber, ReturnDate.parse("2020-02-21")));

        assertThrows(IllegalArgumentException.class, () -> loanQueryService.findBy(itemNumber));
    }

    @Test
    void 会員が現在借りている全貸出取得時に返却した貸出が含まれない() {
        ItemNumber itemNumber = new ItemNumber("2-B");
        registerLoan(itemNumber, 2);
        returnBookRecordService.returned(new Returned(itemNumber, ReturnDate.parse("2020-02-21")));
        LoanStatus loanStatus = loanQueryService.status(new MemberNumber(2));

        assertEquals(loanStatus.count(), 0);
    }

    private void registerLoan(ItemNumber itemNumber, int memberNumber) {
        MemberNumber member = new MemberNumber(memberNumber);
        LoanRequest loanRequest = new LoanRequest(member, itemNumber, LoanDate.parse("2020-02-20"));
        loanRecordService.loaned(loanRequest);
    }
}