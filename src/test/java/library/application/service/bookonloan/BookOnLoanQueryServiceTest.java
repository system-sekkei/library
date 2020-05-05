package library.application.service.bookonloan;

import library.LibraryDBTest;
import library.application.service.holding.HoldingQueryService;
import library.application.service.member.MemberQueryService;
import library.application.service.returnbook.ReturnBookRecordService;
import library.domain.model.loan.loan.BookOnLoan;
import library.domain.model.loan.loan.LoanDate;
import library.domain.model.loan.rule.BookOnLoanRequest;
import library.domain.model.loan.rule.MemberAllBookOnLoans;
import library.domain.model.loan.loan.ReturnDate;
import library.domain.model.loan.loan.ReturningBookOnLoan;
import library.domain.model.item.ItemNumber;
import library.domain.model.item.HoldingInStock;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.type.date.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@LibraryDBTest
class BookOnLoanQueryServiceTest {
    @Autowired
    BookOnLoanQueryService bookOnLoanQueryService;

    @Autowired
    ReturnBookRecordService returnBookRecordService;

    @Autowired
    BookOnLoanRecordService bookOnLoanRecordService;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    HoldingQueryService holdingQueryService;

    @Test
    void 貸出図書を取得できる() {
        registerBookOnLoan(new ItemNumber("2-A"), 1);

        BookOnLoan bookOnLoan = bookOnLoanQueryService.findBookOnLoanByItemNumber(new ItemNumber("2-A"));

        assertEquals(bookOnLoan.member().memberNumber().value(), 1);
    }

    @Test
    void 返却された貸出図書は取得できない() {
        ItemNumber itemNumber = new ItemNumber("2-B");
        registerBookOnLoan(itemNumber, 1);
        BookOnLoan bookOnLoan = bookOnLoanQueryService.findBookOnLoanByItemNumber(itemNumber);
        returnBookRecordService.registerReturnBook(new ReturningBookOnLoan(bookOnLoan, new ReturnDate(Date.from("2020-02-21"))));

        assertThrows(IllegalArgumentException.class, () -> {
            bookOnLoanQueryService.findBookOnLoanByItemNumber(itemNumber);
        });
    }

    @Test
    void 会員が現在借りている全貸出図書を取得できる() {
        registerBookOnLoan(new ItemNumber("2-A"), 2);

        Member member = memberQueryService.findMember(new MemberNumber(2));
        MemberAllBookOnLoans memberAllBookOnLoans = bookOnLoanQueryService.findMemberAllBookOnLoans(member);

        assertEquals(memberAllBookOnLoans.bookOnLoans().numberOfBookOnLoans().value(), 1);
    }

    @Test
    void 会員が現在借りている全貸出図書取得時に返却した貸出図書が含まれない() {
        ItemNumber itemNumber = new ItemNumber("2-B");
        registerBookOnLoan(itemNumber, 2);
        BookOnLoan bookOnLoan = bookOnLoanQueryService.findBookOnLoanByItemNumber(itemNumber);
        returnBookRecordService.registerReturnBook(new ReturningBookOnLoan(bookOnLoan, new ReturnDate(Date.from("2020-02-21"))));

        Member member = memberQueryService.findMember(new MemberNumber(2));
        MemberAllBookOnLoans memberAllBookOnLoans = bookOnLoanQueryService.findMemberAllBookOnLoans(member);

        assertEquals(memberAllBookOnLoans.bookOnLoans().numberOfBookOnLoans().value(), 0);
    }

    private void registerBookOnLoan(ItemNumber itemNumber, int memberNumber) {
        Member member = memberQueryService.findMember(new MemberNumber(memberNumber));
        HoldingInStock holdingInStock = holdingQueryService.findHoldingInStock(itemNumber);
        BookOnLoanRequest bookOnLoanRequest = new BookOnLoanRequest(member, holdingInStock, new LoanDate(Date.from("2020-02-20")));
        bookOnLoanRecordService.registerBookOnLoan(bookOnLoanRequest);
    }
}