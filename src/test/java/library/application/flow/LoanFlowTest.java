package library.application.flow;

import library.ScenarioTest;
import library.application.scenario.LoanScenario;
import library.application.scenario.ReturnsScenario;
import library.application.service.item.ItemQueryService;
import library.application.service.loan.LoanQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.loan.LoanDate;
import library.domain.model.loan.LoanRequest;
import library.domain.model.loan.rule.Loanability;
import library.domain.model.loan.rule.ItemLoanability;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.member.MemberNumber;
import library.domain.model.returned.ReturnDate;
import library.domain.model.returned.Returned;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * TODO: 本来シナリオクラスのテストに書きたくない業務フローのテストをここに退避しているので、置き場所・書き方を決める
 */
@ScenarioTest
class LoanFlowTest {
    @Autowired
    LoanScenario loanScenario;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    LoanQueryService loanQueryService;

    @Autowired
    ItemQueryService itemQueryService;

    @Autowired
    ReturnsScenario returnsScenario;


    @Test
    void 小学生以下は15点まで所蔵品を借りることができる() {
        List<String> list = List.of("1-B", "1-C", "2-A", "2-B", "2-C", "2-D", "2-E", "2-F", "2-G", "2-H", "3-A", "4-A", "5-A", "6-A");
        for (String code : list) {
            LoanRequest loanRequest =
                    generate(2, code, LoanDate.now().toString());
            loanScenario.loan(loanRequest);
        }

        LoanRequest loanRequest =
                generate(2, "8-A", LoanDate.now().toString());

        Loanability loanability = loanScenario.loanability(loanRequest);

        assertSame(loanability, Loanability.貸出可能);

        for (String code : list) {
            返却(code);
        }
    }

    @Test
    void 中学生以上は20点まで所蔵品を借りることができる() {
        List<String> list = List.of("1-B", "1-C", "2-A", "2-B", "2-C", "2-D", "2-E", "2-F", "2-G", "2-H", "3-A", "4-A",
                "5-A", "6-A", "7-A", "8-A", "8-B", "9-A", "9-B");
        for (String code : list) {
            LoanRequest loanRequest =
                    generate(3, code, LoanDate.now().toString());
            loanScenario.loan(loanRequest);
        }

        LoanRequest loanRequest =
                generate(3, "10-A", LoanDate.now().toString());

        Loanability loanability = loanScenario.loanability(loanRequest);

        assertSame(Loanability.貸出可能, loanability);

        for (String code : list) {
            返却(code);
        }
    }

    @Test
    void 視聴覚資料を５点まで貸し出すことができる() {
        List<LoanRequest> requests = List.of(
                generate(1, "11-A", LoanDate.now().toString()),
                generate(1, "12-A", LoanDate.now().toString()),
                generate(1, "13-A", LoanDate.now().toString()),
                generate(1, "14-A", LoanDate.now().toString()),
                generate(1, "15-A", LoanDate.now().toString())
        );

        Loanability loanability = null;
        for (LoanRequest r : requests) {
            loanability = loanScenario.loanability(r);
            if (loanability == Loanability.貸出可能) {
                loanScenario.loan(r);
            }
        }

        assertSame(loanability, Loanability.貸出可能);

        返却("11-A");
        返却("12-A");
        返却("13-A");
        返却("14-A");
        返却("15-A");
    }

    @Test
    void 五点を超える視聴覚資料を貸し出すことはできない() {
        List<LoanRequest> requests = List.of(
                generate(1, "11-A", LoanDate.now().toString()),
                generate(1, "12-A", LoanDate.now().toString()),
                generate(1, "13-A", LoanDate.now().toString()),
                generate(1, "14-A", LoanDate.now().toString()),
                generate(1, "15-A", LoanDate.now().toString()),
                generate(1, "16-A", LoanDate.now().toString())
        );

        Loanability loanability = null;
        for (LoanRequest r : requests) {
            loanability = loanScenario.loanability(r);
            if (loanability == Loanability.貸出可能) {
                loanScenario.loan(r);
            }
        }

        assertSame(loanability, Loanability.視聴覚資料貸出不可);

        返却("11-A");
        返却("12-A");
        返却("13-A");
        返却("14-A");
        返却("15-A");
    }

    @Test
    void 貸出中の所蔵品は貸し出すことができない() {
        LoanRequest loanRequest =
                generate(1, "1-A", "2020-02-20");
        loanScenario.loan(loanRequest);

        ItemLoanability 貸出可能な所蔵品かどうか = loanScenario.貸出可能な所蔵品かどうか(new ItemNumber("1-A"));
        assertSame(貸出可能な所蔵品かどうか, ItemLoanability.貸出中により貸出不可能);

        返却("1-A");
    }

    @Test
    void 貸出制限冊数を超える会員には図書を貸し出すことができない() {
        List<String> list = List.of("1-B", "1-C", "2-A", "2-B", "2-C", "2-D", "2-E", "2-F", "2-G", "2-H", "3-A", "4-A", "5-A", "6-A", "7-A");
        for (String code : list) {
            LoanRequest loanRequest =
                    generate(2, code, LoanDate.now().toString());
            loanScenario.loan(loanRequest);
        }

        LoanRequest loanRequest =
                generate(2, "8-A", LoanDate.now().toString());

        Loanability loanability = loanScenario.loanability(loanRequest);

        assertSame(loanability, Loanability.冊数制限により貸出不可);

        for (String code : list) {
            返却(code);
        }
    }

    @Test
    void 十五日以上延滞している資料がある会員は所蔵品を新たに借りることができない() {
        // 借りた当日を含むため1日足している
        LoanRequest 十五日延滞している貸出 =
                generate(2, "2-A", new LoanDate(LocalDate.now().minusDays(15).minusDays(15).plusDays(1)).toString());
        loanScenario.loan(十五日延滞している貸出);

        LoanRequest loanRequest =
                generate(2, "8-A", LoanDate.now().toString());

        Loanability loanability = loanScenario.loanability(loanRequest);

        assertSame(loanability, Loanability.新規貸出不可);

        返却("2-A");
    }

    @Test
    @Disabled
    void 予約がない資料の貸出を15日間延長できる() {

    }

    @Test
    @Disabled
    void 二回目の貸出延長はできない() {
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
