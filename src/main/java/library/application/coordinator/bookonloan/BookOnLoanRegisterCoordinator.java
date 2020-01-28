package library.application.coordinator.bookonloan;

import library.application.service.bookonloan.BookOnLoanQueryService;
import library.application.service.member.MemberQueryService;
import org.springframework.stereotype.Service;

/**
 * 貸出図書登録コーディネーター
 */
@Service
public class BookOnLoanRegisterCoordinator {
    MemberQueryService memberQueryService;
    BookOnLoanQueryService bookOnLoanQueryService;
    BookOnLoanQueryCoordinator bookOnLoanQueryCoordinator;

    public BookOnLoanRegisterCoordinator(MemberQueryService memberQueryService, BookOnLoanQueryService bookOnLoanQueryService, BookOnLoanQueryCoordinator bookOnLoanQueryCoordinator) {
        this.memberQueryService = memberQueryService;
        this.bookOnLoanQueryService = bookOnLoanQueryService;
        this.bookOnLoanQueryCoordinator = bookOnLoanQueryCoordinator;
    }
}
