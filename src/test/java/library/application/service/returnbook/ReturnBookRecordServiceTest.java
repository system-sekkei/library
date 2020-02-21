package library.application.service.returnbook;

import library.LibraryDBTest;
import library.application.service.bookcollection.BookCollectionQueryService;
import library.application.service.bookonloan.BookOnLoanQueryService;
import library.application.service.bookonloan.BookOnLoanRecordService;
import library.application.service.member.MemberQueryService;
import library.domain.model.bookcollection.BookCollection;
import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookonloan.loan.BookOnLoan;
import library.domain.model.bookonloan.loan.LoanDate;
import library.domain.model.bookonloan.loaning.LoaningOfBookCollection;
import library.domain.model.bookonloan.returning.ReturnDate;
import library.domain.model.bookonloan.returning.ReturningBookOnLoan;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
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
    BookCollectionQueryService bookCollectionQueryService;

    @Test
    void 返却を登録できる() {
        BookOnLoan bookOnLoan = bookOnLoanQueryService.findBookOnLoanByBookCollectionCode(new BookCollectionCode("1-A"));

        ReturningBookOnLoan returningBookOnLoan = new ReturningBookOnLoan(bookOnLoan, new ReturnDate(Date.from("2020-02-20")));
        returnBookRecordService.registerReturnBook(returningBookOnLoan);

        assertThrows(NullPointerException.class, () -> {
            bookOnLoanQueryService.findBookOnLoanByBookCollectionCode(new BookCollectionCode("1-A"));
        });
    }

    private void registerBookOnLoan(BookCollectionCode bookCollectionCode){
        Member member = memberQueryService.findMember(new MemberNumber(1));
        BookCollection bookCollection = bookCollectionQueryService.findBookCollection(bookCollectionCode);
        LoaningOfBookCollection loaningOfBookCollection = new LoaningOfBookCollection(member, bookCollection, new LoanDate(Date.from("2020-02-20")));
        bookOnLoanRecordService.registerBookOnLoan(loaningOfBookCollection);
    }
}