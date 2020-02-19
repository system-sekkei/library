package library.application.coordinator.bookonloan;

import library.application.service.bookcollection.BookCollectionQueryService;
import library.application.service.bookonloan.BookOnLoanQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.bookcollection.BookCollectionStatus;
import library.domain.model.bookonloan.LoaningOfBook;
import library.domain.model.bookonloan.MemberAllBookOnLoans;
import org.springframework.stereotype.Service;

/**
 * 貸出図書登録コーディネーター
 */
@Service
public class BookOnLoanRegisterCoordinator {
    MemberQueryService memberQueryService;
    BookCollectionQueryService bookCollectionQueryService;
    BookOnLoanQueryService bookOnLoanQueryService;

    public BookOnLoanRegisterCoordinator(
            MemberQueryService memberQueryService,
            BookCollectionQueryService bookCollectionQueryService,
            BookOnLoanQueryService bookOnLoanQueryService) {
        this.memberQueryService = memberQueryService;
        this.bookCollectionQueryService = bookCollectionQueryService;
        this.bookOnLoanQueryService = bookOnLoanQueryService;
    }

    public BookOnLoanValidResult isValid(LoaningOfBook loaningOfBook) {
        if (loaningOfBook.bookCollection().bookCollectionStatus() == BookCollectionStatus.貸出中) {
            return BookOnLoanValidResult.貸出中の蔵書;
        }

        MemberAllBookOnLoans memberAllBookOnLoans = bookOnLoanQueryService.findMemberAllBookOnLoans(loaningOfBook.member());
        if (!memberAllBookOnLoans.canBorrowBookToday()) {
            return BookOnLoanValidResult.貸出制限エラー;
        }

        return BookOnLoanValidResult.貸出可能;
    }
}
