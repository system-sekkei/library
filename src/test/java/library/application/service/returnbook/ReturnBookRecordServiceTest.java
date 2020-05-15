package library.application.service.returnbook;

import library.LibraryDBTest;
import library.application.service.bookonloan.LoanQueryService;
import library.application.service.bookonloan.LoanRegisterService;
import library.application.service.holding.ItemQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.loan.loan.Loan;
import library.domain.model.loan.returned.ReturnDate;
import library.domain.model.loan.returned.Returned;
import library.domain.model.book.item.ItemNumber;
import library.domain.type.date.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;

@LibraryDBTest
class ReturnBookRecordServiceTest {
    @Autowired
    ReturnBookRecordService returnBookRecordService;

    @Autowired
    LoanQueryService loanQueryService;

    @Autowired
    LoanRegisterService loanRegisterService;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    ItemQueryService itemQueryService;

    @Test
    void 返却を登録できる() {
        ItemNumber itemNumber = new ItemNumber("1-A");
        ReturnDate returnDate = new ReturnDate(Date.from("2020-02-20"));

        Returned returned = new Returned(itemNumber, returnDate);
        returnBookRecordService.registerReturnBook(returned);

        assertThrows(IllegalArgumentException.class, () -> {
            loanQueryService.findLoanByItemNumber(new ItemNumber("1-A"));
        });
    }
}