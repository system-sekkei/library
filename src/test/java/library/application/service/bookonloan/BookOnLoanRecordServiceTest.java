package library.application.service.bookonloan;

import library.LibraryDBTest;
import library.application.service.holding.HoldingQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.bookonloan.loan.BookOnLoan;
import library.domain.model.bookonloan.loan.LoanDate;
import library.domain.model.bookonloan.loaning.BookOnLoanRequest;
import library.domain.model.holding.HoldingCode;
import library.domain.model.holding.HoldingInStock;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.type.date.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@LibraryDBTest
class BookOnLoanRecordServiceTest {
    @Autowired
    BookOnLoanRecordService bookOnLoanRecordService;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    HoldingQueryService holdingQueryService;

    @Autowired
    BookOnLoanQueryService bookOnLoanQueryService;

    @Test
    void 貸出図書を登録できる() {
        Member member = memberQueryService.findMember(new MemberNumber(1));
        HoldingCode holdingCode = new HoldingCode("2-A");
        HoldingInStock holdingInStock = holdingQueryService.findHoldingInStock(holdingCode);
        BookOnLoanRequest bookOnLoanRequest = new BookOnLoanRequest(member, holdingInStock, new LoanDate(Date.from("2020-02-20")));
        bookOnLoanRecordService.registerBookOnLoan(bookOnLoanRequest);

        BookOnLoan bookOnLoan = bookOnLoanQueryService.findBookOnLoanByHoldingCode(holdingCode);

        assertAll(
                () -> assertEquals(bookOnLoan.member().memberNumber().value(), 1),
                () -> assertEquals(bookOnLoan.loanDate().toString(), "2020-02-20"));
    }
}