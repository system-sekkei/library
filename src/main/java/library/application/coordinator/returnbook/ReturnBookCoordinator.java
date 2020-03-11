package library.application.coordinator.returnbook;

import library.application.service.holding.HoldingQueryService;
import library.application.service.bookonloan.BookOnLoanQueryService;
import library.application.service.returnbook.ReturnBookRecordService;
import library.domain.model.holding.HoldingCode;
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
    HoldingQueryService holdingQueryService;

    public ReturnBookCoordinator(ReturnBookRecordService returnBookRecordService, BookOnLoanQueryService bookOnLoanQueryService, HoldingQueryService holdingQueryService) {
        this.returnBookRecordService = returnBookRecordService;
        this.bookOnLoanQueryService = bookOnLoanQueryService;
        this.holdingQueryService = holdingQueryService;
    }

    /**
     * 貸出図書の返却を登録する
     */
    public void returnBook(HoldingCode holdingCode, ReturnDate returnDate) {
        holdingQueryService.findHoldingOnLoan(holdingCode);

        BookOnLoan bookOnLoan = bookOnLoanQueryService.findBookOnLoanByHoldingCode(holdingCode);
        returnBookRecordService.registerReturnBook(new ReturningBookOnLoan(bookOnLoan, returnDate));
    }
}
