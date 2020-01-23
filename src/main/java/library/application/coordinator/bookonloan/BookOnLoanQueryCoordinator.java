package library.application.coordinator.bookonloan;

import library.application.service.bookonloan.BookOnLoanQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.bookonloan.BookOnLoans;
import library.domain.model.bookonloan.MemberAllBookOnLoans;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import org.springframework.stereotype.Service;

/**
 * 貸出図書参照コーディネーター
 */
@Service
public class BookOnLoanQueryCoordinator {
    MemberQueryService memberQueryService;
    BookOnLoanQueryService bookOnLoanQueryService;

    BookOnLoanQueryCoordinator(MemberQueryService memberQueryService, BookOnLoanQueryService bookOnLoanQueryService) {
        this.memberQueryService = memberQueryService;
        this.bookOnLoanQueryService = bookOnLoanQueryService;
    }

    public MemberAllBookOnLoans findMemberAllBookOnLoans(MemberNumber memberNumber) {
        Member member = memberQueryService.findMember(memberNumber);
        BookOnLoans bookOnLoans = bookOnLoanQueryService.findMemberAllBookOnLoans(memberNumber);

        return new MemberAllBookOnLoans(member, bookOnLoans);
    }
}
