package library.application.service.returnbook;

import library.LibraryDBTest;
import library.application.service.bookonloan.BookOnLoanQueryService;
import library.application.service.bookonloan.BookOnLoanRecordService;
import library.application.service.holding.HoldingQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.bookonloan.loan.BookOnLoan;
import library.domain.model.bookonloan.returning.ReturnDate;
import library.domain.model.bookonloan.returning.ReturningBookOnLoan;
import library.domain.model.holding.HoldingCode;
import library.domain.type.date.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;

@LibraryDBTest
class ReturnBookRecordServiceTest {
    @Autowired
    ReturnBookRecordService returnBookRecordService;

    @Autowired
    BookOnLoanQueryService bookOnLoanQueryService;

    @Autowired
    BookOnLoanRecordService bookOnLoanRecordService;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    HoldingQueryService holdingQueryService;

    @Test
    void 返却を登録できる() {
        BookOnLoan bookOnLoan = bookOnLoanQueryService.findBookOnLoanByHoldingCode(new HoldingCode("1-A"));

        ReturningBookOnLoan returningBookOnLoan = new ReturningBookOnLoan(bookOnLoan, new ReturnDate(Date.from("2020-02-20")));
        returnBookRecordService.registerReturnBook(returningBookOnLoan);

        assertThrows(IllegalArgumentException.class, () -> {
            bookOnLoanQueryService.findBookOnLoanByHoldingCode(new HoldingCode("1-A"));
        });
    }
}