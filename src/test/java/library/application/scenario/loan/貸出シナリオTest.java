package library.application.scenario.loan;

import library.application.fixture.entry.EntryFixture;
import library.application.fixture.item.ItemFixture;
import library.application.fixture.member.MemberFixture;
import library.application.service.loan.LoanQueryService;
import library.domain.model.loan.LoanDate;
import library.domain.model.loan.LoanRequest;
import library.domain.model.loan.rule.ItemLoanability;
import library.domain.model.loan.rule.LoanStatus;
import library.domain.model.loan.rule.Loanability;
import library.domain.model.material.entry.*;
import library.domain.model.material.item.Item;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.member.MemberType;
import library.domain.model.member.Name;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("integration-test")
class 貸出シナリオTest {

    @Autowired
    LoanScenario sut;

    @Autowired
    MemberFixture memberFixture;

    @Autowired
    EntryFixture entryFixture;

    @Autowired
    ItemFixture itemFixture;

    @Autowired
    LoanQueryService loanQueryService;

    @Test
    void memberStatus() {
        fail();
    }

    Member 高橋雄一 = new Member(new MemberNumber(1009), new Name("高橋雄一"), MemberType.中学生以上);
    Entry entry = new Entry(new EntryNumber(898912), new Title("マイクロサービスアーキテクチャー"), new WorkOf("Sam Newman"), EntryType.図書);
    Item item = new Item(new ItemNumber("77878"), entry);

    @Test
    void 貸出制限を判断する() {
        // Arrange
        memberFixture.会員を登録する(高橋雄一);
        entryFixture.所蔵品目を登録する(entry);
        itemFixture.所蔵品を登録する(item);

        LoanRequest 貸出依頼 =
                new LoanRequest(new MemberNumber(1009), new ItemNumber("77878"), new LoanDate(LocalDate.parse("2023-07-01")));

        // Act
        Loanability 貸出可否 = sut.loanability(貸出依頼);
        // Assert
        assertEquals(Loanability.貸出可能, 貸出可否);
    }

    @Test
    void 貸出() {
        // Arrange
        memberFixture.会員を登録する(高橋雄一);
        entryFixture.所蔵品目を登録する(entry);
        itemFixture.所蔵品を登録する(item);

        // Act
        sut.loan(new LoanRequest(高橋雄一.number(), item.所蔵品番号(), new LoanDate(LocalDate.parse("2023-07-04"))));

        // Assert
        //sut.loanStatus(); // TODO 引数は会員が良い？　
        LoanStatus status = loanQueryService.status(高橋雄一.number());
        assertEquals(1, status.loans().冊数().value());
    }

    @Test
    @Disabled("LoanScenario#loanStatus()の引数は会員が良いと思う")
    void loanStatus() {
        fail();
    }

    @Test
    void 所蔵品の貸出可否を提示する() {
        // Arrange
        entryFixture.所蔵品目を登録する(entry);
        itemFixture.所蔵品を登録する(item);
        itemFixture.貸出可能な状態を登録する(item);

        // TODO 所蔵品の番号がわかるということは実物を手に持っているということだから所蔵品の状態は取得しなくても良いのでは？
        //   予約時に所蔵品目(Entry)番号から、全所蔵品の状態を取得することはある？
        // Act
        ItemLoanability 所蔵品の貸出可否 = sut.所蔵品の貸出可否を提示する(item.所蔵品番号());
        // Assert
        assertEquals(ItemLoanability.貸出可能, 所蔵品の貸出可否);
    }
}