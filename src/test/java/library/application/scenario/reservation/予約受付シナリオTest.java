package library.application.scenario.reservation;

import library.application.fixture.entry.EntryFixture;
import library.application.fixture.item.ItemFixture;
import library.application.fixture.member.MemberFixture;
import library.application.service.reservation.ReservationQueryService;
import library.application.service.reservation.ReservationRepository;
import library.domain.model.material.entry.*;
import library.domain.model.material.instock.EntryInStock;
import library.domain.model.material.instock.EntryInStockList;
import library.domain.model.material.item.Item;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.member.*;
import library.domain.model.reservation.Reservations;
import library.domain.model.reservation.availability.ReservationAvailability;
import library.domain.model.reservation.request.ReservationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class 予約受付シナリオTest {

    @Autowired
    ReservationScenario sut;

    @Autowired
    ReservationQueryService reservationQueryService;

    @Autowired
    MemberFixture memberFixture;
    @Autowired
    EntryFixture entryFixture;
    @Autowired
    ItemFixture itemFixture;
    Entry マイクロサービスアーキテクチャー = new Entry(new EntryNumber(898912), new Title("マイクロサービスアーキテクチャー"), new WorkOf("Sam Newman"), EntryType.図書);
    Entry 現場で役立つシステム設計の原則 = new Entry(new EntryNumber(8998), new Title("現場で役立つシステム設計の原則"), new WorkOf("増田亨"), EntryType.図書);
    Entry 建築の基礎 = new Entry(new EntryNumber(78102), new Title("建築の基礎"), new WorkOf("IHI"), EntryType.図書);
    Entry 現場ねこ写真集 = new Entry(new EntryNumber(12345), new Title("現場ねこ写真集"), new WorkOf("くまみね"), EntryType.図書);

    Item 現場ねこ写真集_1 = new Item(new ItemNumber("80009"), 現場ねこ写真集);
    @Test
    void 所蔵品目をキーワードで検索する() {
        // Arrange
        entryFixture.所蔵品目を登録する(マイクロサービスアーキテクチャー);
        entryFixture.所蔵品目を登録する(現場で役立つシステム設計の原則);
        entryFixture.所蔵品目を登録する(建築の基礎);
        entryFixture.所蔵品目を登録する(現場ねこ写真集);
        itemFixture.所蔵品を登録する(現場ねこ写真集_1);

        // Act
        EntryInStockList entries = sut.search(new Keyword("現場"));

        // Assert
        EntryInStock 現場ねこ写真集検索結果 =
                entries.asList().stream().filter(entry -> entry.entry().title().toString().equals("現場ねこ写真集")).findFirst().get();
        assertAll(
                () -> assertEquals(2, entries.size()),
                () -> assertEquals("×", 現場ねこ写真集検索結果.showInStock())
        );
    }

    @Test
    void 所蔵品目を取得する() {
        entryFixture.所蔵品目を登録する(建築の基礎);
        entryFixture.所蔵品目を登録する(現場ねこ写真集);

        Entry material = sut.findMaterial(建築の基礎.entryNumber());

        assertEquals("建築の基礎 (IHI)", material.show());
    }

    Member 高橋雄一 = new Member(new MemberNumber(1009), new Name("高橋雄一"), MemberType.中学生以上);
    @Test
    void 会員の状態を取得する() {
        memberFixture.会員を登録する(高橋雄一);

        MemberStatus memberStatus = sut.memberStatus(高橋雄一.number());

        assertEquals(MemberStatus.有効, memberStatus);
    }

    @Test
    void 予約依頼に対する予約可否を取得する() {
        // ARRANGE
        memberFixture.会員を登録する(高橋雄一);
        entryFixture.所蔵品目を登録する(現場ねこ写真集);
        ReservationRequest 予約依頼 = new ReservationRequest(高橋雄一.number(), 現場ねこ写真集.entryNumber());
        // ACT
        ReservationAvailability result = sut.reservationAvailability(予約依頼);
        // ASSERT
        assertEquals(ReservationAvailability.予約可能, result);
    }

    @Test
    void 予約を記録する() {
        memberFixture.会員を登録する(高橋雄一);
        entryFixture.所蔵品目を登録する(現場ねこ写真集);

        sut.reserve(現場ねこ写真集, 高橋雄一.number());

        Reservations 予約一覧 = reservationQueryService.予約一覧(高橋雄一.number());
        assertEquals("1件", 予約一覧.numberOfReservation().toString());
    }
}