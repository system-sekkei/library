package library.application.scenario.returns;

import library.application.fixture.entry.EntryFixture;
import library.application.fixture.item.ItemFixture;
import library.application.fixture.member.MemberFixture;
import library.application.scenario.loan.LoanScenario;
import library.application.service.loan.LoanQueryService;
import library.domain.model.loan.LoanDate;
import library.domain.model.loan.LoanRequest;
import library.domain.model.loan.rule.LoanStatus;
import library.domain.model.material.entry.*;
import library.domain.model.material.item.Item;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.member.MemberType;
import library.domain.model.member.Name;
import library.domain.model.returned.ReturnDate;
import library.domain.model.returned.Returned;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("integration-test")
class 返却シナリオTest {

    @Autowired
    ReturnsScenario sut;

    @Autowired
    LoanScenario loanScenario;
    @Autowired
    LoanQueryService loanQueryService;


    @Test
    void 返却する() {
        // Arrange
        memberFixture.会員を登録する(高橋雄一);
        entryFixture.所蔵品目を登録する(entry);
        itemFixture.所蔵品を登録する(item);

        LoanRequest 貸出依頼 = new LoanRequest(高橋雄一.number(), item.所蔵品番号(), new LoanDate(LocalDate.parse("2023-07-04")));
        loanScenario.loan(貸出依頼);

        Returned 返却 = new Returned(item.所蔵品番号(), ReturnDate.parse("2023-02-19"));

        // Act
        sut.returned(返却);

        LoanStatus status = loanQueryService.status(高橋雄一.number());
        assertTrue(status.loans().asList().isEmpty());
    }

    Member 高橋雄一 = new Member(new MemberNumber(1009), new Name("高橋雄一"), MemberType.中学生以上);
    Entry entry = new Entry(new EntryNumber(898912), new Title("マイクロサービスアーキテクチャー"), new WorkOf("Sam Newman"), EntryType.図書);
    Item item = new Item(new ItemNumber("77878"), entry);

    @Autowired
    MemberFixture memberFixture;

    @Autowired
    EntryFixture entryFixture;

    @Autowired
    ItemFixture itemFixture;
}