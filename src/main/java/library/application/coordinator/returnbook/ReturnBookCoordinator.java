package library.application.coordinator.returnbook;

import library.application.service.bookonloan.BookOnLoanQueryService;
import library.application.service.holding.HoldingQueryService;
import library.application.service.returnbook.ReturnBookRecordService;
import library.domain.model.loan.loan.BookOnLoan;
import library.domain.model.loan.loan.ReturnDate;
import library.domain.model.loan.loan.ReturningBookOnLoan;
import library.domain.model.book.item.ItemNumber;
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
    public void returnBook(ItemNumber itemNumber, ReturnDate returnDate) {
        holdingQueryService.findHoldingOnLoan(itemNumber);

        BookOnLoan bookOnLoan = bookOnLoanQueryService.findBookOnLoanByItemNumber(itemNumber);
        returnBookRecordService.registerReturnBook(new ReturningBookOnLoan(bookOnLoan, returnDate));
    }
}
