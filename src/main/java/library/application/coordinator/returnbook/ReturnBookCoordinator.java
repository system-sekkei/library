package library.application.coordinator.returnbook;

import library.application.coordinator.bookonloan.LoaningResult;
import library.application.service.bookcollection.BookCollectionQueryService;
import library.application.service.bookonloan.BookOnLoanQueryService;
import library.application.service.returnbook.ReturnBookRecordService;
import library.domain.model.bookcollection.BookCollection;
import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookonloan.loan.BookOnLoan;
import library.domain.model.bookonloan.returning.ReturnDate;
import library.domain.model.bookonloan.returning.ReturningBookOnLoan;
import org.springframework.stereotype.Service;

/**
 * 返却コーディネーター
 */
@Service
public class ReturnBookCoordinator {
    ReturnBookRecordService returnBookRecordService;
    BookOnLoanQueryService bookOnLoanQueryService;
    BookCollectionQueryService bookCollectionQueryService;

    public ReturnBookCoordinator(ReturnBookRecordService returnBookRecordService, BookOnLoanQueryService bookOnLoanQueryService, BookCollectionQueryService bookCollectionQueryService) {
        this.returnBookRecordService = returnBookRecordService;
        this.bookOnLoanQueryService = bookOnLoanQueryService;
        this.bookCollectionQueryService = bookCollectionQueryService;
    }

    /**
     * 貸出図書の返却を登録する
     */
    public ReturnBookResult returnBook(BookCollectionCode bookCollectionCode, ReturnDate returnDate) {
        BookCollection bookCollection = bookCollectionQueryService.findBookCollection(bookCollectionCode);
        ReturnBookResult result = ReturnBookResult.from(bookCollection.bookCollectionStatus());

        if (result.ok()) {
            BookOnLoan bookOnLoan = bookOnLoanQueryService.findBookOnLoanByBookCollectionCode(bookCollectionCode);
            returnBookRecordService.registerReturnBook(new ReturningBookOnLoan(bookOnLoan, returnDate));
        }

        return result;
    }
}
