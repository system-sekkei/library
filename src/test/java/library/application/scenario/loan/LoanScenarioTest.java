package library.application.scenario.loan;

import library.LibraryDBTest;
import library.application.scenario.LoanScenario;
import library.application.service.loan.LoanQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.loan.LoanDate;
import library.domain.model.loan.rule.Loanability;
import library.domain.model.member.MemberNumber;
import library.domain.model.loan.LoanRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@LibraryDBTest
class LoanScenarioTest {
    @Autowired
    LoanScenario loanScenario;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    LoanQueryService loanQueryService;

    @Test
    void 図書を貸し出すことができる() {
        LoanRequest loanRequest =
                generate(1, "2-A", "2020-02-20");
        Loanability loanability = loanScenario.loanability(loanRequest);

        assertSame(loanability , Loanability.貸出可能);
    }

    @Test
    void 視聴覚資料を貸し出すことができる() {
        LoanRequest loanRequest =
                generate(1, "8-A", "2020-02-20");
        Loanability loanability = loanScenario.loanability(loanRequest);

        assertSame(loanability , Loanability.貸出可能);
    }

    @Test
    void 五点を超える視聴覚資料を貸し出すことはできない() {
        List<LoanRequest> requests = List.of(
                generate(1, "8-A", "2020-02-20"),
                generate(1, "9-A", "2020-02-20"),
                generate(1, "10-A", "2020-02-20"),
                generate(1, "11-A", "2020-02-20"),
                generate(1, "12-A", "2020-02-20"),
                generate(1, "13-A", "2020-02-20")
        );

        Loanability loanability = null;
        for (LoanRequest r : requests) {
            loanability = loanScenario.loanability(r);
            if (loanability == Loanability.貸出可能) {
                loanScenario.loan(r);
            }
        }

        assertSame(loanability, Loanability.視聴覚資料貸出不可);
    }

    // FIXME 貸出可能になる
    //@Test
    void 貸出中の所蔵品は貸し出すことができない() {
        LoanRequest loanRequest =
                generate(2, "2-B", LoanDate.now().toString());
        Loanability loanability = loanScenario.loanability(loanRequest);
        assertNotSame(loanability , Loanability.貸出可能);
    }

    @Test
    void 貸出制限冊数を超える会員には図書を貸し出すことができない() {
        List<String> list = List.of("1-B", "1-C", "2-A", "2-B", "2-C", "2-D", "2-E", "2-F", "2-G", "2-H", "3-A", "4-A", "5-A", "6-A", "7-A");
        for (String code : list) {
            LoanRequest loanRequest =
                    generate(2, code, "2020-02-20");
            loanScenario.loan(loanRequest);
        }

        LoanRequest loanRequest =
                generate(2, "8-A", "2020-02-20");

        Loanability loanability = loanScenario.loanability(loanRequest);

        assertNotSame(loanability, Loanability.貸出可能);
    }

    private LoanRequest generate(int memberNumber, String itemNumber, String loanDate) {
        MemberNumber member = new MemberNumber(memberNumber);
        ItemNumber item = new ItemNumber(itemNumber);
        return new LoanRequest(member, item, LoanDate.parse(loanDate));
    }
}