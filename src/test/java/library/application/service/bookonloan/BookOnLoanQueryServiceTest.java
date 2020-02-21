package library.application.service.bookonloan;

import library.application.service.bookcollection.BookCollectionQueryService;
import library.application.service.member.MemberQueryService;
import library.application.service.returnbook.ReturnBookRecordService;
import library.domain.model.bookcollection.BookCollection;
import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookonloan.*;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.type.date.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
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
        registerBookOnLoan(new BookCollectionCode("1-A"));
        BookOnLoan bookOnLoan = bookOnLoanQueryService.findBookOnLoanByBookCollectionCode(new BookCollectionCode("1-A"));

        assertEquals(bookOnLoan.member().memberNumber().value(), 1);
    }

    @Test
    void 返却された貸出図書は取得できない() {
        BookCollectionCode bookCollectionCode = new BookCollectionCode("1-A");
        registerBookOnLoan(bookCollectionCode);
        BookOnLoan bookOnLoan = bookOnLoanQueryService.findBookOnLoanByBookCollectionCode(bookCollectionCode);
        returnBookRecordService.registerReturnBook(new ReturningBookOnLoan(bookOnLoan, new ReturnDate(Date.from("2020-02-21"))));

        assertThrows(NullPointerException.class, () -> {
            bookOnLoanQueryService.findBookOnLoanByBookCollectionCode(bookCollectionCode);
        });
    }

    private void registerBookOnLoan(BookCollectionCode bookCollectionCode){
        Member member = memberQueryService.findMember(new MemberNumber(1));
        BookCollection bookCollection = bookCollectionQueryService.findBookCollection(bookCollectionCode);
        LoaningOfBook loaningOfBook = new LoaningOfBook(member, bookCollection, new LoanDate(Date.from("2020-02-20")));
        bookOnLoanRecordService.registerBookOnLoan(loaningOfBook);
    }
}