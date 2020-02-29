package library.application.coordinator.bookonloan;

import library.application.service.bookcollection.BookCollectionQueryService;
import library.application.service.bookonloan.BookOnLoanQueryService;
import library.application.service.bookonloan.BookOnLoanRecordService;
import library.application.service.member.MemberQueryService;
import library.domain.model.bookonloan.loan.BookOnLoan;
import library.domain.model.bookonloan.loaning.*;
import org.springframework.stereotype.Service;

/**
 * 貸出図書登録コーディネーター
 */
@Service
public class BookOnLoanRegisterCoordinator {
    MemberQueryService memberQueryService;
    BookCollectionQueryService bookCollectionQueryService;
    BookOnLoanQueryService bookOnLoanQueryService;
    BookOnLoanRecordService bookOnLoanRecordService;

    public BookOnLoanRegisterCoordinator(
            MemberQueryService memberQueryService,
            BookCollectionQueryService bookCollectionQueryService,
            BookOnLoanQueryService bookOnLoanQueryService,
            BookOnLoanRecordService bookOnLoanRecordService) {
        this.memberQueryService = memberQueryService;
        this.bookCollectionQueryService = bookCollectionQueryService;
        this.bookOnLoanQueryService = bookOnLoanQueryService;
        this.bookOnLoanRecordService = bookOnLoanRecordService;
    }

    /**
     * 図書の貸出受付
     */
    public LoaningCard loaning(BookOnLoanRequest bookOnLoanRequest) {
        MemberAllBookOnLoans memberAllBookOnLoans = bookOnLoanQueryService.findMemberAllBookOnLoans(bookOnLoanRequest.member());

        if (memberAllBookOnLoans.canBorrowBookToday() == CanLoan.貸出不可) {
            return new LoaningCard(RejectReason.貸出冊数超過);
        }

        BookOnLoan bookOnLoan = bookOnLoanRecordService.registerBookOnLoan(bookOnLoanRequest);
        return new LoaningCard(bookOnLoan);
    }

}
