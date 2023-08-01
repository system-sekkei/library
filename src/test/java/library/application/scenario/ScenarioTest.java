package library.application.scenario;

import library.LibraryDBTest;
import library.application.scenario.loan.LoanScenario;
import library.application.service.loan.LoanQueryService;
import library.domain.model.loan.Loan;
import library.domain.model.loan.LoanDate;
import library.domain.model.loan.LoanRequest;
import library.domain.model.loan.rule.ItemLoanability;
import library.domain.model.loan.rule.Loanability;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.member.MemberNumber;
import library.domain.model.member.MemberStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static library.domain.model.member.MemberStatus.有効;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@LibraryDBTest
public class ScenarioTest {

    @Autowired
    LoanScenario loanScenario;

    @Autowired
    LoanQueryService loanQueryService;

    @Test
    void 有効な会員に貸出可能な所蔵品を貸し出す() {
        // 準備
        // TODO: いまはとりあえずdata.sqlのデータを利用。シナリオ単位で準備できるようにしたい。
        MemberNumber memberNumber = new MemberNumber(1);
        ItemNumber itemNumber = new ItemNumber("1-A");
        LoanDate loanDate = LoanDate.parse("2020-02-20");
        LoanRequest loanRequest = new LoanRequest(memberNumber, itemNumber, loanDate);

        // 実行
        MemberStatus memberStatus = loanScenario.memberStatus(loanRequest);
        assertTrue(memberStatus == 有効, "会員番号が有効");

        ItemLoanability 所蔵品の貸出可否 = loanScenario.所蔵品の貸出可否を提示する(itemNumber);
        assertSame(所蔵品の貸出可否, ItemLoanability.貸出可能, "所蔵品が貸出可能");

        Loanability 貸出可否 = loanScenario.loanability(loanRequest);
        assertSame(貸出可否, Loanability.貸出可能, "貸出希望の会員に貸出可能");

        loanScenario.loan(loanRequest);

        // 確認
        Loan loan = loanQueryService.findBy(itemNumber);

        assertTrue(loan.member().number().sameValue(memberNumber), "会員に貸し出すことができている");
        assertTrue(loan.date().sameValue(loanDate), "貸出日が指定の貸し出し日になっている");
    }


}
