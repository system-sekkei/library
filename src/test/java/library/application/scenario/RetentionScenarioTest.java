package library.application.scenario;

import library.LibraryDBTest;
import library.application.service.material.MaterialQueryService;
import library.application.service.member.MemberQueryService;
import library.application.service.reservation.ReservationQueryService;
import library.application.service.reservation.ReservationRecordService;
import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.request.Reservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@LibraryDBTest
class RetentionScenarioTest {
    @Autowired
    RetentionScenario retentionScenario;

    @Autowired
    ReservationQueryService reservationQueryService;

    @Autowired
    ReservationRecordService reservationRecordService;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    MaterialQueryService materialQueryService;

    // @Test
    void 未準備の予約を一覧できる() {

    }

    // @Test
    void 未準備の予約所蔵品を取り置くことができる() {

    }

    // @Test
    void 取置中の所蔵品を予約者に貸し出すことができる() {

    }

    // @Test
    void 取置中の所蔵品を予約者以外に貸し出すことはできない() {

    }

    // @Test
    void 予約が取り消された取置を一覧できる() {

    }

    // @Test
    void 取置中の所蔵品を在庫に戻すことができる() {
    }
}