package library.application.scenario;

import library.ScenarioTest;
import library.application.service.loan.LoanQueryService;
import library.domain.model.loan.Loan;
import library.domain.model.loan.LoanDate;
import library.domain.model.loan.LoanRequest;
import library.domain.model.loan.rule.LoanStatus;
import library.domain.model.material.item.ItemLoanability;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.member.MemberNumber;
import library.domain.model.member.MemberStatus;
import library.domain.model.returned.ReturnDate;
import library.domain.model.returned.Returned;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static library.domain.model.member.MemberStatus.有効;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ScenarioTest
class LoanScenarioTest {
    @Autowired
    LoanScenario loanScenario;

    @Autowired
    LoanQueryService loanQueryService;

    @Autowired
    ReturnsScenario returnsScenario;

    @Test
    void 会員番号の有効性を確認する() {
        LoanRequest loanRequest =
                generate(1, "2-A", "2020-02-20");
        MemberStatus memberStatus = loanScenario.memberStatus(loanRequest);

        assertTrue(memberStatus == 有効);
    }

    @Test
    void 図書を貸し出す() {
        LoanRequest loanRequest =
                generate(1, "2-A", "2020-02-20");
        loanScenario.loan(loanRequest);

        Loan loan = loanQueryService.findBy(loanRequest.itemNumber());

        assertTrue(loan.item().所蔵品番号().sameValue(new ItemNumber("2-A")));

        返却("2-A");
    }

    @Test
    void 貸出制限を判断する() {
        LoanRequest loanRequest =
                generate(1, "1-A", "2020-02-20");
        loanScenario.loan(loanRequest);

        ItemLoanability 貸出可能な所蔵品かどうか = loanScenario.貸出可能な所蔵品かどうか(new ItemNumber("1-A"));
        assertSame(貸出可能な所蔵品かどうか, ItemLoanability.貸出中により貸出不可能);

        返却("1-A");
    }

    @Test
    void 貸出状況を提示する() {
        LoanRequest loanRequest =
                generate(1, "2-A", "2020-02-20");
        loanScenario.loan(loanRequest);

        LoanStatus loanStatus = loanScenario.loanStatus(loanRequest);

        assertTrue(loanStatus.memberNumber().sameValue(new MemberNumber(1)));

        返却("2-A");
    }

    private LoanRequest generate(int memberNumber, String itemNumber, String loanDate) {
        MemberNumber member = new MemberNumber(memberNumber);
        ItemNumber item = new ItemNumber(itemNumber);
        return new LoanRequest(member, item, LoanDate.parse(loanDate));
    }

    private void 返却(String itemNumber) {
        Returned returned = new Returned(new ItemNumber(itemNumber), ReturnDate.now());
        returnsScenario.returned(returned);
    }
}
