package library.application.coordinator.loan;

import library.LibraryDBTest;
import library.application.service.item.ItemQueryService;
import library.application.service.loan.LoanQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.item.Item;
import library.domain.model.item.ItemNumber;
import library.domain.model.loan.loan.LoanDate;
import library.domain.model.loan.loan.LoanRequest;
import library.domain.model.loan.rule.RestrictionResult;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@LibraryDBTest
class LoanCoordinatorTest {
    @Autowired
    LoanCoordinator loanCoordinator;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    ItemQueryService itemQueryService;

    @Autowired
    LoanQueryService loanQueryService;

    @Test
    void 図書を貸し出すことができる() {
        LoanRequest loanRequest =
                generate(1, "2-A", "2020-02-20");
        RestrictionResult restrictionResult = loanCoordinator.shouldRestrict(loanRequest);

        assertTrue(restrictionResult == RestrictionResult.貸出可能);
    }

    // FIXME 貸出可能になる
    //@Test
    void 貸出中の蔵書は貸し出すことができない() {
        LoanRequest loanRequest =
                generate(2, "2-B", LoanDate.now().toString());
        RestrictionResult restrictionResult = loanCoordinator.shouldRestrict(loanRequest);
        assertTrue(restrictionResult != RestrictionResult.貸出可能);
    }

    @Test
    void 貸出制限冊数を超える会員には図書を貸し出すことができない() {
        List<String> list = List.of("2-C", "2-D", "2-E", "2-F", "2-G");
        for (String code : list) {
            LoanRequest loanRequest =
                    generate(3, code, "2020-02-20");
            loanCoordinator.loan(loanRequest);
        }

        LoanRequest loanRequest =
                generate(3, "2-H", "2020-02-20");

        RestrictionResult restrictionResult = loanCoordinator.shouldRestrict(loanRequest);

        assertTrue(restrictionResult != RestrictionResult.貸出可能);
    }

    private LoanRequest generate(int memberNumber, String itemNumber, String loanDate) {
        Member member = memberQueryService.findMember(new MemberNumber(memberNumber));
        Item itemInStock = itemQueryService.findItemInStock(new ItemNumber(itemNumber));
        return new LoanRequest(member, itemInStock, LoanDate.parse(loanDate));
    }
}