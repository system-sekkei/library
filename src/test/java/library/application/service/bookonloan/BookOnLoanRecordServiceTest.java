package library.application.service.bookonloan;

import library.LibraryDBTest;
import library.application.service.holding.ItemQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.loan.loan.BookOnLoan;
import library.domain.model.loan.loan.LoanDate;
import library.domain.model.loan.rule.BookOnLoanRequest;
import library.domain.model.book.item.ItemNumber;
import library.domain.model.book.item.ItemInStock;
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
    ItemQueryService itemQueryService;

    @Autowired
    BookOnLoanQueryService bookOnLoanQueryService;

    @Test
    void 貸出図書を登録できる() {
        Member member = memberQueryService.findMember(new MemberNumber(1));
        ItemNumber itemNumber = new ItemNumber("2-A");
        ItemInStock itemInStock = itemQueryService.findHoldingInStock(itemNumber);
        BookOnLoanRequest bookOnLoanRequest = new BookOnLoanRequest(member, itemInStock, new LoanDate(Date.from("2020-02-20")));
        bookOnLoanRecordService.registerBookOnLoan(bookOnLoanRequest);

        BookOnLoan bookOnLoan = bookOnLoanQueryService.findBookOnLoanByItemNumber(itemNumber);

        assertAll(
                () -> assertEquals(bookOnLoan.member().memberNumber().value(), 1),
                () -> assertEquals(bookOnLoan.loanDate().toString(), "2020-02-20"));
    }
}