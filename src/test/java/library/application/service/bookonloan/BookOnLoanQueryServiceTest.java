package library.application.service.bookonloan;

import library.LibraryDBTest;
import library.application.service.bookcollection.BookCollectionQueryService;
import library.application.service.member.MemberQueryService;
import library.application.service.returnbook.ReturnBookRecordService;
import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookcollection.BookCollectionInStock;
import library.domain.model.bookcollection.BookCollectionOnLoan;
import library.domain.model.bookonloan.loan.BookOnLoan;
import library.domain.model.bookonloan.loan.LoanDate;
import library.domain.model.bookonloan.loaning.BookOnLoanRequest;
import library.domain.model.bookonloan.loaning.MemberAllBookOnLoans;
import library.domain.model.bookonloan.returning.ReturnDate;
import library.domain.model.bookonloan.returning.ReturningBookOnLoan;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.type.date.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

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
    BookCollectionQueryService bookCollectionQueryService;

    @Test
    void 貸出図書を取得できる() {
        registerBookOnLoan(new BookCollectionCode("2-A"), 1);

        BookOnLoan bookOnLoan = bookOnLoanQueryService.findBookOnLoanByBookCollectionCode(new BookCollectionCode("2-A"));

        assertEquals(bookOnLoan.member().memberNumber().value(), 1);
    }

    @Test
    void 返却された貸出図書は取得できない() {
        BookCollectionCode bookCollectionCode = new BookCollectionCode("2-B");
        registerBookOnLoan(bookCollectionCode, 1);
        BookOnLoan bookOnLoan = bookOnLoanQueryService.findBookOnLoanByBookCollectionCode(bookCollectionCode);
        returnBookRecordService.registerReturnBook(new ReturningBookOnLoan(bookOnLoan, new ReturnDate(Date.from("2020-02-21"))));

        assertThrows(IllegalArgumentException.class, () -> {
            bookOnLoanQueryService.findBookOnLoanByBookCollectionCode(bookCollectionCode);
        });
    }

    @Test
    void 会員が現在借りている全貸出図書を取得できる() {
        registerBookOnLoan(new BookCollectionCode("2-A"), 2);

        Member member = memberQueryService.findMember(new MemberNumber(2));
        MemberAllBookOnLoans memberAllBookOnLoans = bookOnLoanQueryService.findMemberAllBookOnLoans(member);

        assertEquals(memberAllBookOnLoans.bookOnLoans().numberOfBookOnLoans().value(), 1);
    }

    @Test
    void 会員が現在借りている全貸出図書取得時に返却した貸出図書が含まれない() {
        BookCollectionCode bookCollectionCode = new BookCollectionCode("2-B");
        registerBookOnLoan(bookCollectionCode, 2);
        BookOnLoan bookOnLoan = bookOnLoanQueryService.findBookOnLoanByBookCollectionCode(bookCollectionCode);
        returnBookRecordService.registerReturnBook(new ReturningBookOnLoan(bookOnLoan, new ReturnDate(Date.from("2020-02-21"))));

        Member member = memberQueryService.findMember(new MemberNumber(2));
        MemberAllBookOnLoans memberAllBookOnLoans = bookOnLoanQueryService.findMemberAllBookOnLoans(member);

        assertEquals(memberAllBookOnLoans.bookOnLoans().numberOfBookOnLoans().value(), 0);
    }

    private void registerBookOnLoan(BookCollectionCode bookCollectionCode, int memberNumber){
        Member member = memberQueryService.findMember(new MemberNumber(memberNumber));
        BookCollectionInStock bookCollection = bookCollectionQueryService.findBookCollectionInStock(bookCollectionCode);
        BookOnLoanRequest bookOnLoanRequest = new BookOnLoanRequest(member, bookCollection, new LoanDate(Date.from("2020-02-20")));
        bookOnLoanRecordService.registerBookOnLoan(bookOnLoanRequest);
    }
}