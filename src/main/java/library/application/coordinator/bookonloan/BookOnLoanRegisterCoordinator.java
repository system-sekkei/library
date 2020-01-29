package library.application.coordinator.bookonloan;

import library.application.service.bookcollection.BookCollectionQueryService;
import library.application.service.bookonloan.BookOnLoanQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.bookonloan.BookOnLoan;
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
    BookOnLoanQueryCoordinator bookOnLoanQueryCoordinator;

    public BookOnLoanRegisterCoordinator(
        MemberQueryService memberQueryService,
        BookCollectionQueryService bookCollectionQueryService,
        BookOnLoanQueryService bookOnLoanQueryService,
        BookOnLoanQueryCoordinator bookOnLoanQueryCoordinator) {
        this.memberQueryService = memberQueryService;
        this.bookCollectionQueryService = bookCollectionQueryService;
        this.bookOnLoanQueryService = bookOnLoanQueryService;
        this.bookOnLoanQueryCoordinator = bookOnLoanQueryCoordinator;
    }

    public BookOnLoanValidResult isValid(BookOnLoan bookOnLoan) {
        if (memberQueryService.findMember(bookOnLoan.memberNumber()) == null) {
            return BookOnLoanValidResult.存在しない会員番号;
        }

        if (bookCollectionQueryService.findBookCollection(bookOnLoan.bookCollectionCode()) == null) {
            return BookOnLoanValidResult.存在しない蔵書コード;
        }

        MemberAllBookOnLoans memberAllBookOnLoans = bookOnLoanQueryCoordinator.findMemberAllBookOnLoans(bookOnLoan.memberNumber());
        if (!memberAllBookOnLoans.canBorrowBookToday()) {
            return BookOnLoanValidResult.貸出制限エラー;
        }


        return BookOnLoanValidResult.正常;
    }
}
