package library.application.scenario;

import library.ScenarioTest;
import library.application.service.item.ItemQueryService;
import library.application.service.loan.LoanQueryService;
import library.application.service.material.MaterialQueryService;
import library.application.service.member.MemberQueryService;
import library.application.service.reservation.ReservationQueryService;
import library.application.service.reservation.ReservationRecordService;
import library.domain.model.loan.Loan;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.ReservationNumber;
import library.domain.model.reservation.request.ReservationRequest;
import library.domain.model.retention.Retained;
import library.domain.model.retention.Retention;
import library.domain.model.retention.wait.ReservationWithWaitingOrder;
import library.domain.model.retention.wait.ReservationWithWaitingOrderList;
import library.domain.model.returned.ReturnDate;
import library.domain.model.returned.Returned;
import library.infrastructure.datasource.retention.RetentionDatasource;
import library.infrastructure.datasource.retention.RetentionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static library.domain.model.retention.availability.RetentionAvailability.取置不可;
import static library.domain.model.retention.availability.RetentionAvailability.取置可能;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ScenarioTest
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

    @Autowired
    ItemQueryService itemQueryService;

    @Autowired
    RetentionDatasource retentionDatasource;

    @Autowired
    LoanScenario loanScenario;

    @Autowired
    RetentionMapper retentionMapper;

    @Autowired
    LoanQueryService loanQueryService;

    @Autowired
    ReturnsScenario returnsScenario;

    @Test
    void 未準備の予約を一覧できる() {
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
    void 在庫がある未準備の予約が取置可能であることがわかる() {
        ReservationRequest reservationRequest = new ReservationRequest(new MemberNumber(2), new EntryNumber(2));
        reservationRecordService.reserve(reservationRequest);

        ReservationWithWaitingOrder 未準備の予約 = retentionScenario
                .未準備の予約一覧().asList().stream()
                .filter(r -> r.memberNumber().sameValue(new MemberNumber(2))).toList().get(0);

        assertEquals(取置可能, 未準備の予約.retentionAvailability());

        取置(未準備の予約.reservationNumber().toString(), "2-A");
        貸出("2-A");
        返却("2-A");
    }

    @Test
    void 在庫がない未準備の予約が取置不可であることがわかる() {
        EntryNumber entryNumber = new EntryNumber(4);
        ReservationRequest reservationRequest = new ReservationRequest(new MemberNumber(3), entryNumber);
        reservationRecordService.reserve(reservationRequest);

        ReservationRequest reservationRequest2 = new ReservationRequest(new MemberNumber(4), entryNumber);
        reservationRecordService.reserve(reservationRequest2);

        List<ReservationWithWaitingOrder> 未準備の予約一覧 = retentionScenario.未準備の予約一覧().asList();
        ReservationWithWaitingOrder 未準備の予約 = new ReservationWithWaitingOrder(null, null, null);
        ReservationWithWaitingOrder 取置不可の未準備の予約 = new ReservationWithWaitingOrder(null, null, null);
        for (ReservationWithWaitingOrder 予約 : 未準備の予約一覧) {
            if (予約.memberNumber().sameValue(new MemberNumber(3)))
                未準備の予約 = 予約;
            else if (予約.memberNumber().sameValue(new MemberNumber(4)))
                取置不可の未準備の予約 = 予約;
        }

        assertEquals(取置不可, 取置不可の未準備の予約.retentionAvailability());

        取置(未準備の予約.reservationNumber().toString(), "4-A");
        貸出("4-A");
        返却("4-A");

        取置(取置不可の未準備の予約.reservationNumber().toString(), "4-A");
        貸出("4-A");
        返却("4-A");
    }

    @Test
    void 未準備の予約所蔵品を取り置くことができる() {
        ReservationRequest reservationRequest = new ReservationRequest(new MemberNumber(4), new EntryNumber(2));
        reservationRecordService.reserve(reservationRequest);

        ItemNumber itemNumber = new ItemNumber("2-A");

        ReservationNumber reservationNumber = retentionScenario
                .未準備の予約一覧().asList().stream()
                .filter(r -> r.memberNumber().sameValue(new MemberNumber(4))).toList().get(0).reservationNumber();

        Retention 未準備の予約された所蔵品 = new Retention(reservationNumber, itemNumber);
        retentionScenario.retain(未準備の予約された所蔵品);

        Retained 取置資料 = retentionDatasource.findBy(itemNumber);

        assertTrue(取置資料.reservationNumber().sameValue(reservationNumber));

        貸出("2-A");
        返却("2-A");
    }

    @Test
    void 取置中の所蔵品を予約者に貸し出すことができる() {
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

    // @Test
    void 取置中の所蔵品を予約者以外に貸し出すことはできない() {

    }

    // @Test
    void 予約が取り消された取置を一覧できる() {

    }

    // @Test
    void 取置中の所蔵品を在庫に戻すことができる() {
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