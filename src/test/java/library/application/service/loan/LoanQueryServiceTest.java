package library.application.service.loan;

import library.LibraryDBTest;
import library.application.service.item.ItemQueryService;
import library.application.service.member.MemberQueryService;
import library.application.service.returns.ReturnBookRecordService;
import library.domain.model.item.ItemNumber;
import library.domain.model.loan.loan.Loan;
import library.domain.model.loan.loan.LoanDate;
import library.domain.model.loan.returned.ReturnDate;
import library.domain.model.loan.returned.Returned;
import library.domain.model.loan.rule.LoanStatus;
import library.domain.model.member.MemberNumber;
import library.domain.model.loan.loan.LoanRequest;
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
    LoanRegisterService loanRegisterService;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    ItemQueryService itemQueryService;

    @Test
    void 貸出図書を取得できる() {
        registerLoan(new ItemNumber("2-A"), 1);

        Loan loan = loanQueryService.findLoanByItemNumber(new ItemNumber("2-A"));

        assertEquals(loan.member().number().value(), 1);
    }

    @Test
    void 返却された貸出図書は取得できない() {
        ItemNumber itemNumber = new ItemNumber("2-B");
        registerLoan(itemNumber, 1);
        returnBookRecordService.registerReturnBook(new Returned(itemNumber, ReturnDate.parse("2020-02-21")));

        assertThrows(IllegalArgumentException.class, () -> {
            loanQueryService.findLoanByItemNumber(itemNumber);
        });
    }

    @Test
    void 会員が現在借りている全貸出図書取得時に返却した貸出図書が含まれない() {
        ItemNumber itemNumber = new ItemNumber("2-B");
        registerLoan(itemNumber, 2);
        returnBookRecordService.registerReturnBook(new Returned(itemNumber, ReturnDate.parse("2020-02-21")));
        LoanStatus loanStatus = loanQueryService.loanStatusOf(new MemberNumber(2));

        assertEquals(loanStatus.count(), 0);
    }

    private void registerLoan(ItemNumber itemNumber, int memberNumber) {
        MemberNumber member = new MemberNumber(memberNumber);
        LoanRequest loanRequest = new LoanRequest(member, itemNumber, LoanDate.parse("2020-02-20"));
        loanRegisterService.registerLoan(loanRequest);
    }
}