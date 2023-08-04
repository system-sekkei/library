package library.application.scenario.retention;

import library.application.fixture.entry.EntryFixture;
import library.application.fixture.item.ItemFixture;
import library.application.fixture.member.MemberFixture;
import library.application.scenario.reservation.ReservationScenario;
import library.application.service.reservation.ReservationQueryService;
import library.domain.model.material.entry.*;
import library.domain.model.material.item.Item;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.member.MemberType;
import library.domain.model.member.Name;
import library.domain.model.reservation.Reservation;
import library.domain.model.reservation.Reservations;
import library.domain.model.retention.Retained;
import library.domain.model.retention.RetainedList;
import library.domain.model.retention.Retention;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("integration-test")
class 取置期限切れシナリオTest {

    @Autowired
    RetentionExpireScenario sut;
    @Autowired
    RetentionScenario retentionScenario;
    @Autowired
    ReservationScenario reservationScenario;
    @Autowired
    ReservationQueryService reservationQueryService;

    @Test
    void 取置を期限切れにする() {
        // Arrange
        Reservations 予約一覧 = reservationQueryService.予約一覧(高橋雄一.number());
        Reservation 予約 = 予約一覧.asList().get(0);
        Retention 取置依頼 = new Retention(予約.reservationNumber(), 建築の基礎_1.所蔵品番号());
        retentionScenario.retain(取置依頼);
        RetainedList retainedList = retentionScenario.retainedList();
        Retained 取置き = retainedList.asList().get(0);

        // Act
        sut.expire(取置き.retentionNumber());

        // Assert
        RetainedList 取置き取消後の取置き一覧 = retentionScenario.retainedList();
        assertTrue(取置き取消後の取置き一覧.isEmpty());

    }


    @Autowired
    MemberFixture memberFixture;
    @Autowired
    EntryFixture entryFixture;
    @Autowired
    ItemFixture itemFixture;

    Entry 建築の基礎 = new Entry(new EntryNumber(78102), new Title("建築の基礎"), new WorkOf("IHI"), EntryType.図書);
    Item 建築の基礎_1 = new Item(new ItemNumber("810008"), 建築の基礎);

    Member 高橋雄一 = new Member(new MemberNumber(1009), new Name("高橋雄一"), MemberType.中学生以上);
    @BeforeEach
    void extracted() {
        memberFixture.会員を登録する(高橋雄一);

        entryFixture.所蔵品目を登録する(建築の基礎);
        itemFixture.所蔵品を登録する(建築の基礎_1);

        reservationScenario.reserve(建築の基礎, 高橋雄一.number());
    }
}