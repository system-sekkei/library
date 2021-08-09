package library.application.scenario.retention;

import library.LibraryDBTest;
import library.application.scenario.RetentionScenario;
import library.application.service.member.MemberQueryService;
import library.application.service.material.MaterialQueryService;
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

    @Test
    void 取置可能な貸出予約図書一覧を出力できる() {
        Member member = memberQueryService.findMember(new MemberNumber(1));
        Entry entry = materialQueryService.findMaterial(new EntryNumber(2));
        Reservation reservation = Reservation.of(member, entry);
        reservationRecordService.reserve(reservation);

        // TODO 仕様から再定義
//        Reservations reservations = retentionCoordinator.retention();
//        Reservation reservation1 = reservations.asList().get(0);
//
//        assertAll(
//                () ->assertTrue(reservation1.reservedMaterial().material().sameMaterial(material)),
//                () -> assertEquals(1, reservation1.member().memberNumber().value()));
    }
}