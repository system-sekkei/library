package library.application.service.bookonloan;

import library.LibraryDBTest;
import library.application.service.bookcollection.BookCollectionQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookcollection.BookCollectionOnLoan;
import library.domain.model.bookonloan.loan.BookOnLoan;
import library.domain.model.bookonloan.loan.LoanDate;
import library.domain.model.bookonloan.loaning.BookOnLoanRequest;
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
    BookCollectionQueryService bookCollectionQueryService;

    @Autowired
    BookOnLoanQueryService bookOnLoanQueryService;

    @Test
    void 貸出図書を登録できる() {
        Member member = memberQueryService.findMember(new MemberNumber(1));
        BookCollectionCode bookCollectionCode = new BookCollectionCode("2-A");
        BookCollectionOnLoan bookCollection = bookCollectionQueryService.findBookCollectionOnLoan(bookCollectionCode);
        BookOnLoanRequest bookOnLoanRequest = new BookOnLoanRequest(member, bookCollection.bookCollection(), new LoanDate(Date.from("2020-02-20")));
        bookOnLoanRecordService.registerBookOnLoan(bookOnLoanRequest);

        BookOnLoan bookOnLoan = bookOnLoanQueryService.findBookOnLoanByBookCollectionCode(bookCollectionCode);

        assertAll(
                () -> assertEquals(bookOnLoan.member().memberNumber().value(), 1),
                () -> assertEquals(bookOnLoan.loanDate().toString(), "2020-02-20"));
    }
}