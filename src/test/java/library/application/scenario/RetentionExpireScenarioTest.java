package library.application.scenario;

import library.ScenarioTest;
import library.application.service.reservation.ReservationQueryService;
import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.wait.ReservationWithWaitingOrder;
import library.domain.model.retention.Retained;
import library.domain.model.retention.Retention;
import library.infrastructure.datasource.retention.RetentionDatasource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static library.domain.model.material.entry.EntryType.図書;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ScenarioTest
class RetentionExpireScenarioTest {
    @Autowired
    RetentionExpireScenario retentionExpireScenario;

    @Autowired
    ReservationScenario reservationScenario;

    @Autowired
    ReservationQueryService reservationQueryService;

    @Autowired
    RetentionScenario retentionScenario;

    @Autowired
    RetentionDatasource retentionDatasource;

    @Test
    void 期限内に受け取らなかった取置を無効にする() {
        EntryNumber entryNumber = new EntryNumber(2);
        MemberNumber memberNumber = new MemberNumber(2);
        reservationScenario.reserve(new Entry(entryNumber, null, null, 図書), memberNumber);

        ReservationWithWaitingOrder reservation = reservationQueryService
                .未準備の予約一覧().asList().stream()
                .filter(r -> r.memberNumber().sameValue(memberNumber)).toList().get(0);

        ItemNumber itemNumber = new ItemNumber("2-A");
        Retention 未準備の予約された所蔵品 = new Retention(reservation.reservationNumber(), itemNumber);
        retentionScenario.retain(未準備の予約された所蔵品);

        Retained retained = retentionDatasource.findBy(itemNumber);

        retentionExpireScenario.expire(retained.retentionNumber());

        assertTrue(retentionDatasource.retentions().isEmpty());
    }
}