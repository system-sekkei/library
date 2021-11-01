package library.application.scenario;

import library.ScenarioTest;
import library.application.service.reservation.ReservationQueryService;
import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.Reservation;
import library.domain.model.reservation.wait.ReservationWithWaitingOrder;
import library.domain.model.retention.Retention;
import library.domain.model.returned.ReturnDate;
import library.domain.model.returned.Returned;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static library.domain.model.material.entry.EntryType.図書;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ScenarioTest
public class ReservationScenarioTest {
    @Autowired
    ReservationScenario reservationScenario;

    @Autowired
    ReservationQueryService reservationQueryService;

    @Autowired
    ReturnsScenario returnsScenario;

    @Autowired
    RetentionScenario retentionScenario;

    @Test
    void 所蔵品目を予約をする() {
        EntryNumber entryNumber = new EntryNumber(2);
        MemberNumber memberNumber = new MemberNumber(2);
        reservationScenario.reserve(new Entry(entryNumber, null, null, 図書), memberNumber);

        ReservationWithWaitingOrder reservation = reservationQueryService
                .未準備の予約一覧().asList().stream()
                .filter(r -> r.memberNumber().sameValue(memberNumber)).toList().get(0);

        assertTrue(reservation.memberNumber().sameValue(memberNumber));

        取置(reservation.reservation(), "2-A");
        貸出("2-A");
        返却("2-A");
    }

    private void 取置(Reservation reservation, String itemNumber) {
        Retention 未準備の予約された所蔵品 = reservation.toRetention(new ItemNumber(itemNumber));
        retentionScenario.retain(未準備の予約された所蔵品);
    }

    private void 貸出(String itemNumber) {
        retentionScenario.loan(new ItemNumber(itemNumber));
    }

    private void 返却(String itemNumber) {
        Returned returned = new Returned(new ItemNumber(itemNumber), ReturnDate.now());
        returnsScenario.returned(returned);
    }
}
