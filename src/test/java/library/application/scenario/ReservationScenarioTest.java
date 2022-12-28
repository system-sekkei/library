package library.application.scenario;

import library.ScenarioTest;
import library.application.scenario.reservation.ReservationScenario;
import library.application.scenario.retention.RetentionScenario;
import library.application.scenario.returns.ReturnsScenario;
import library.application.service.reservation.ReservationQueryService;
import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.material.entry.Keyword;
import library.domain.model.material.instock.EntryInStockList;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.member.MemberNumber;
import library.domain.model.member.MemberStatus;
import library.domain.model.reservation.ReservationNumber;
import library.domain.model.reservation.availability.ReservationAvailability;
import library.domain.model.reservation.request.ReservationRequest;
import library.domain.model.reservation.wait.ReservationWithWaitingOrder;
import library.domain.model.retention.Retention;
import library.domain.model.returned.ReturnDate;
import library.domain.model.returned.Returned;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static library.domain.model.material.entry.EntryType.図書;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void 本を探す() {
        EntryInStockList 検索結果 = reservationScenario.search(new Keyword("オブジェクト指向"));

        assertEquals(検索結果.size(), 2);
    }

    @Test
    void 本を見つける() {
        EntryNumber entryNumber = new EntryNumber(2);
        Entry 所蔵品目 = reservationScenario.findMaterial(entryNumber);

        assertTrue(所蔵品目.entryNumber().sameValue(entryNumber));
    }

    @Test
    void 会員番号の有効性を確認する() {
        MemberNumber memberNumber = new MemberNumber(2);
        MemberStatus memberStatus = reservationScenario.memberStatus(memberNumber);

        assertEquals(MemberStatus.有効, memberStatus);
    }

    @Test
    void 予約制限を判断する() {
        EntryNumber entryNumber = new EntryNumber(2);
        MemberNumber memberNumber = new MemberNumber(2);

        ReservationRequest 予約依頼 = new ReservationRequest(memberNumber, entryNumber);
        ReservationAvailability 予約可否 = reservationScenario.reservationAvailability(予約依頼);

        assertEquals(ReservationAvailability.予約可能, 予約可否);
    }

    @Test
    void 予約を記録する() {
        EntryNumber entryNumber = new EntryNumber(2);
        MemberNumber memberNumber = new MemberNumber(2);
        reservationScenario.reserve(new Entry(entryNumber, null, null, 図書), memberNumber);

        ReservationWithWaitingOrder reservation = reservationQueryService
                .未準備の予約一覧().asList().stream()
                .filter(r -> r.memberNumber().sameValue(memberNumber)).toList().get(0);

        assertTrue(reservation.memberNumber().sameValue(memberNumber));

        取置(reservation.reservationNumber().toString(), "2-A");
        貸出("2-A");
        返却("2-A");
    }

    private void 取置(String reservationNumber, String itemNumber) {
        Retention 未準備の予約された所蔵品 = new Retention(new ReservationNumber(reservationNumber), new ItemNumber(itemNumber));
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
