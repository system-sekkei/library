package library.application.coordinator.bookonloan;

import library.application.service.bookcollection.BookCollectionQueryService;
import library.application.service.bookonloan.BookOnLoanQueryService;
import library.application.service.bookonloan.BookOnLoanRecordService;
import library.application.service.member.MemberQueryService;
import library.domain.model.bookonloan.loaning.LoaningCard;
import library.domain.model.bookonloan.loaning.BookOnLoanRequest;
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

    /**
     * 図書の貸出受付
     */
    public LoaningCard loaning(BookOnLoanRequest bookOnLoanRequest) {
        LoaningCard result = LoaningCard.from(bookOnLoanRequest.bookCollection().bookCollectionStatus());
        if (result.hasError()) {
            return result;
        }

        MemberAllBookOnLoans memberAllBookOnLoans = bookOnLoanQueryService.findMemberAllBookOnLoans(bookOnLoanRequest.member());
        LoaningCard loaningCard = LoaningCard.from(memberAllBookOnLoans.canBorrowBookToday());

        if (loaningCard.ok()) {
            bookOnLoanRecordService.registerBookOnLoan(bookOnLoanRequest);
        }

        return loaningCard;
    }

}
