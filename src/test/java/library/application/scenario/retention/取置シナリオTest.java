package library.application.scenario.retention;

import library.application.fixture.entry.EntryFixture;
import library.application.fixture.item.ItemFixture;
import library.application.fixture.member.MemberFixture;
import library.application.scenario.reservation.ReservationScenario;
import library.application.service.loan.LoanQueryService;
import library.application.service.reservation.ReservationQueryService;
import library.domain.model.loan.rule.LoanStatus;
import library.domain.model.material.entry.*;
import library.domain.model.material.item.Item;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.material.item.ItemStatus;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.member.MemberType;
import library.domain.model.member.Name;
import library.domain.model.reservation.Reservation;
import library.domain.model.reservation.Reservations;
import library.domain.model.reservation.wait.ReservationWithWaitingOrderList;
import library.domain.model.retention.MaterialMatching;
import library.domain.model.retention.RetainedList;
import library.domain.model.retention.Retention;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@ActiveProfiles("integration-test")
class 取置シナリオTest {

    @Autowired
    RetentionScenario sut;
    @Autowired
    RetentionScenario retentionScenario; // テスト対象をはっきりさせるため同じインスタンスを別名で宣言

    @Autowired
    ReservationScenario reservationScenario;
    @Autowired
    ReservationQueryService reservationQueryService;
    @Autowired
    LoanQueryService loanQueryService;


    @Test
    void 未準備の予約一覧を取得する() {
        // Act
        ReservationWithWaitingOrderList 未準備の予約一覧 = sut.未準備の予約一覧();
        // Assert
        assertEquals("2件", 未準備の予約一覧.numberOfReservation().toString());
    }

    @Test
    void 取り置く() {
        // Arrange
        Reservations 予約一覧 = reservationQueryService.予約一覧(高橋雄一.number());
        Reservation 予約 = 予約一覧.asList().get(0);

        Retention 取置依頼 = new Retention(予約.reservationNumber(), 現場ねこ写真集_1.所蔵品番号());
        // Act
        sut.retain(取置依頼);

        // Assert
        RetainedList retainedList = sut.retainedList();
        assertEquals("1件を取り置いています", retainedList.showCount());
    }

    @Test
    void 準備完了した取置を一覧を取得() {
        // Arrange
        Reservations 予約一覧 = reservationQueryService.予約一覧(高橋雄一.number());
        Reservation 予約1 = 予約一覧.asList().get(0);
        Reservation 予約2 = 予約一覧.asList().get(1);

        // 取置きを登録
        retentionScenario.retain(new Retention(予約1.reservationNumber(), 現場ねこ写真集_1.所蔵品番号()));
        retentionScenario.retain(new Retention(予約2.reservationNumber(), 建築の基礎_1.所蔵品番号()));

        // Act
        RetainedList retainedList = sut.retainedList();
        // Assert
        assertEquals("2件を取り置いています", retainedList.showCount());
    }

    @Test
    void 予約を見つける() {
        Reservations 予約一覧 = reservationQueryService.予約一覧(高橋雄一.number());
        Reservation 予約 = 予約一覧.asList().get(0);

        Reservation 見つかった予約 = sut.reservationOf(予約.reservationNumber());

        assertEquals(予約.reservationNumber().toString(), 見つかった予約.reservationNumber().toString());
    }

    @Test
    void 予約された本であることを確認する() {
        Reservations 予約一覧 = reservationQueryService.予約一覧(高橋雄一.number());
        Reservation 予約 = 予約一覧.asList().get(0);

        MaterialMatching materialMatching =
                sut.isSameMaterial(予約, new Retention(予約.reservationNumber(), 現場ねこ写真集_1.所蔵品番号()));

        assertEquals(MaterialMatching.一致, materialMatching);
    }

    @Test
    void 所蔵品の状態を確認する() {
        itemFixture.貸出可能な状態を登録する(現場ねこ写真集_1);

        ItemStatus itemStatus = sut.itemStatus(現場ねこ写真集_1.所蔵品番号());

        assertEquals(ItemStatus.在庫中, itemStatus);
    }

    @Test
    void 取置を貸し出す() {
        // Arrange
        Reservations 予約一覧 = reservationQueryService.予約一覧(高橋雄一.number());
        Reservation 予約 = 予約一覧.asList().get(0);
        // 取置きを登録
        retentionScenario.retain(new Retention(予約.reservationNumber(), 現場ねこ写真集_1.所蔵品番号()));

        // Act
        sut.loan(現場ねこ写真集_1.所蔵品番号());

        // Assert
        LoanStatus status = loanQueryService.status(高橋雄一.number());
        assertEquals(1, status.loans().冊数().value());
    }

    @Autowired
    MemberFixture memberFixture;
    @Autowired
    EntryFixture entryFixture;
    @Autowired
    ItemFixture itemFixture;

    Entry 現場ねこ写真集 = new Entry(new EntryNumber(12345), new Title("現場ねこ写真集"), new WorkOf("くまみね"), EntryType.図書);
    Entry 建築の基礎 = new Entry(new EntryNumber(78102), new Title("建築の基礎"), new WorkOf("IHI"), EntryType.図書);
    Item 現場ねこ写真集_1 = new Item(new ItemNumber("80009"), 現場ねこ写真集);
    Item 建築の基礎_1 = new Item(new ItemNumber("810008"), 建築の基礎);

    Member 高橋雄一 = new Member(new MemberNumber(1009), new Name("高橋雄一"), MemberType.中学生以上);
    @BeforeEach
    void extracted() {
        memberFixture.会員を登録する(高橋雄一);

        entryFixture.所蔵品目を登録する(現場ねこ写真集);
        entryFixture.所蔵品目を登録する(建築の基礎);
        itemFixture.所蔵品を登録する(現場ねこ写真集_1);
        itemFixture.所蔵品を登録する(建築の基礎_1);

        reservationScenario.reserve(現場ねこ写真集, 高橋雄一.number());
        reservationScenario.reserve(建築の基礎, 高橋雄一.number());
    }

}