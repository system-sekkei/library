package library.application.scenario;

import library.ScenarioTest;
import library.application.scenario.retention.RetentionScenario;
import library.application.scenario.returns.ReturnsScenario;
import library.application.service.loan.LoanQueryService;
import library.application.service.reservation.ReservationRecordService;
import library.domain.model.loan.Loan;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.ReservationNumber;
import library.domain.model.reservation.request.ReservationRequest;
import library.domain.model.retention.Retained;
import library.domain.model.retention.Retention;
import library.domain.model.reservation.wait.ReservationWithWaitingOrder;
import library.domain.model.reservation.wait.ReservationWithWaitingOrderList;
import library.domain.model.returned.ReturnDate;
import library.domain.model.returned.Returned;
import library.infrastructure.datasource.retention.RetentionDatasource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ScenarioTest
class RetentionScenarioTest {
    @Autowired
    RetentionScenario retentionScenario;

    @Autowired
    ReservationRecordService reservationRecordService;

    @Autowired
    RetentionDatasource retentionDatasource;

    @Autowired
    LoanQueryService loanQueryService;

    @Autowired
    ReturnsScenario returnsScenario;

    @Test
    void 未準備の予約を一覧する() {
        ReservationRequest reservationRequest = new ReservationRequest(new MemberNumber(1), new EntryNumber(2));
        reservationRecordService.reserve(reservationRequest);

        ReservationWithWaitingOrderList 未準備の予約一覧 = new ReservationWithWaitingOrderList(retentionScenario.未準備の予約一覧().asList().stream()
                .filter(r -> r.memberNumber().sameValue(new MemberNumber(1))).toList());

        assertEquals(1, 未準備の予約一覧.asList().size());

        ReservationWithWaitingOrder 未準備の予約 = 未準備の予約一覧.asList().get(0);
        取置(未準備の予約.reservationNumber().toString(), "2-A");
        貸出("2-A");
        返却("2-A");
    }

    @Test
    void 取り置く() {
        ReservationRequest reservationRequest = new ReservationRequest(new MemberNumber(4), new EntryNumber(2));
        reservationRecordService.reserve(reservationRequest);

        ItemNumber itemNumber = new ItemNumber("2-A");

        ReservationNumber reservationNumber = retentionScenario
                .未準備の予約一覧().asList().stream()
                .filter(r -> r.memberNumber().sameValue(new MemberNumber(4))).toList().get(0).reservationNumber();

        Retention 未準備の予約された所蔵品 = new Retention(reservationNumber, itemNumber);
        retentionScenario.retain(未準備の予約された所蔵品);

        Retained 取置資料 = retentionDatasource.findBy(itemNumber);

        assertTrue(取置資料.itemNumber().sameValue(itemNumber));

        貸出("2-A");
        返却("2-A");
    }

    @Test
    void 取置を貸し出す() {
        MemberNumber memberNumber = new MemberNumber(5);
        ItemNumber itemNumber = new ItemNumber("3-A");

        // 予約
        ReservationRequest reservationRequest = new ReservationRequest(memberNumber, new EntryNumber(3));
        reservationRecordService.reserve(reservationRequest);

        ReservationNumber reservationNumber = retentionScenario
                .未準備の予約一覧().asList().stream()
                .filter(r -> r.memberNumber().sameValue(new MemberNumber(5))).toList().get(0).reservationNumber();

        Retention 未準備の予約された所蔵品 = new Retention(reservationNumber, itemNumber);
        retentionScenario.retain(未準備の予約された所蔵品);

        // 貸出
        retentionScenario.loan(itemNumber);

        Loan loan = loanQueryService.findBy(itemNumber);

        assertTrue(memberNumber.sameValue(loan.memberNumber()));

        返却("3-A");
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
