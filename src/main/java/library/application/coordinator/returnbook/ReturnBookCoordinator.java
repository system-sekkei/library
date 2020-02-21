package library.application.coordinator.returnbook;

import library.application.service.bookonloan.BookOnLoanQueryService;
import library.application.service.returnbook.ReturnBookRecordService;
import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookonloan.BookOnLoan;
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

    public ReturnBookCoordinator(ReturnBookRecordService returnBookRecordService, BookOnLoanQueryService bookOnLoanQueryService) {
        this.returnBookRecordService = returnBookRecordService;
        this.bookOnLoanQueryService = bookOnLoanQueryService;
    }

    /**
     * 貸出図書の返却を登録する
     */
    public void returnBook(BookCollectionCode bookCollectionCode, ReturnDate returnDate) {
        // TODO: 貸出図書の状態をここでチェックする

        BookOnLoan bookOnLoan = bookOnLoanQueryService.findBookOnLoanByBookCollectionCode(bookCollectionCode);
        returnBookRecordService.registerReturnBook(new ReturningBookOnLoan(bookOnLoan, returnDate));
    }
}
