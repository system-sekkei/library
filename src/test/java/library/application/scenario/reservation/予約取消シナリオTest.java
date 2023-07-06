package library.application.scenario.reservation;

import library.application.fixture.entry.EntryFixture;
import library.application.fixture.member.MemberFixture;
import library.application.service.reservation.ReservationQueryService;
import library.domain.model.material.entry.*;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.member.MemberType;
import library.domain.model.member.Name;
import library.domain.model.reservation.ReservationNumber;
import library.domain.model.reservation.Reservations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("integration-test")
class 予約取消シナリオTest {

    @Autowired
    ReservationCancellationScenario sut;

    @Autowired
    ReservationScenario reservationScenario;
    @Autowired
    ReservationQueryService reservationQueryService;

    @Autowired
    MemberFixture memberFixture;
    @Autowired
    EntryFixture entryFixture;


    Entry 現場ねこ写真集 = new Entry(new EntryNumber(12345), new Title("現場ねこ写真集"), new WorkOf("くまみね"), EntryType.図書);
    Member 高橋雄一 = new Member(new MemberNumber(1009), new Name("高橋雄一"), MemberType.中学生以上);
    @Test
    void 予約を取り消す() {
        // Arrange
        memberFixture.会員を登録する(高橋雄一);
        entryFixture.所蔵品目を登録する(現場ねこ写真集);

        reservationScenario.reserve(現場ねこ写真集, 高橋雄一.number());

        Reservations 予約一覧 = reservationQueryService.予約一覧(高橋雄一.number());
        ReservationNumber 予約番号 = 予約一覧.asList().get(0).reservationNumber();

        // Act
        sut.cancel(予約番号);

        // Assert
        Reservations 取消後予約一覧 = reservationQueryService.予約一覧(高橋雄一.number());
        assertTrue(取消後予約一覧.isEmpty());
    }
}