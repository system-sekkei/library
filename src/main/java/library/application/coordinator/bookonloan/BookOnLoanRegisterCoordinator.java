package library.application.coordinator.bookonloan;

import library.application.service.bookcollection.BookCollectionQueryService;
import library.application.service.bookonloan.BookOnLoanQueryService;
import library.application.service.bookonloan.BookOnLoanRecordService;
import library.application.service.member.MemberQueryService;
import library.domain.model.bookcollection.BookCollectionStatus;
import library.domain.model.bookonloan.loaning.LoaningOfBookCollection;
import library.domain.model.bookonloan.loaning.MemberAllBookOnLoans;
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

    public BookOnLoanValidResult loaning(LoaningOfBookCollection loaningOfBookCollection) {
        if (loaningOfBookCollection.bookCollection().bookCollectionStatus() == BookCollectionStatus.貸出中) {
            return BookOnLoanValidResult.貸出中の蔵書;
        }

        MemberAllBookOnLoans memberAllBookOnLoans = bookOnLoanQueryService.findMemberAllBookOnLoans(loaningOfBookCollection.member());
        BookOnLoanValidResult bookOnLoanValidResult = BookOnLoanValidResult.bookOnLoanValidResult(memberAllBookOnLoans.canBorrowBookToday());

        if (!bookOnLoanValidResult.hasError()) {
            bookOnLoanRecordService.registerBookOnLoan(loaningOfBookCollection);
        }

        return bookOnLoanValidResult;
    }

}
