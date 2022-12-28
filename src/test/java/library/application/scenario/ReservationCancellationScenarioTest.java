package library.application.scenario;

import library.ScenarioTest;
import library.application.scenario.reservation.ReservationCancellationScenario;
import library.application.scenario.reservation.ReservationScenario;
import library.application.service.reservation.ReservationQueryService;
import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.wait.ReservationWithWaitingOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static library.domain.model.material.entry.EntryType.図書;
import static org.junit.jupiter.api.Assertions.*;

@ScenarioTest
class ReservationCancellationScenarioTest {
    @Autowired
    ReservationCancellationScenario reservationCancellationScenario;

    @Autowired
    ReservationScenario reservationScenario;

    @Autowired
    ReservationQueryService reservationQueryService;

    @Test
    void 予約をキャンセルする() {
        EntryNumber entryNumber = new EntryNumber(2);
        MemberNumber memberNumber = new MemberNumber(2);
        reservationScenario.reserve(new Entry(entryNumber, null, null, 図書), memberNumber);

        ReservationWithWaitingOrder reservation = reservationQueryService
                .未準備の予約一覧().asList().stream()
                .filter(r -> r.memberNumber().sameValue(memberNumber)).toList().get(0);

        reservationCancellationScenario.cancel(reservation.reservationNumber());

        assertTrue(reservationQueryService.未準備の予約一覧().isEmpty());
    }
}
