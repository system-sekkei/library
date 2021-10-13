package library.application.scenario;

import library.LibraryDBTest;
import library.application.service.item.ItemQueryService;
import library.application.service.loan.LoanQueryService;
import library.application.service.material.MaterialQueryService;
import library.application.service.member.MemberQueryService;
import library.application.service.reservation.ReservationQueryService;
import library.application.service.reservation.ReservationRecordService;
import library.domain.model.loan.Loan;
import library.domain.model.loan.LoanDate;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.material.item.ItemStatus;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.ReservationStatus;
import library.domain.model.reservation.ReservationNumber;
import library.domain.model.reservation.request.ReservationRequest;
import library.domain.model.reservation.wait.ReservationWithWaitingOrder;
import library.domain.model.reservation.wait.ReservationWithWaitingOrderList;
import library.domain.model.retention.Retained;
import library.domain.model.retention.Retention;
import library.infrastructure.datasource.retention.RetentionDatasource;
import library.infrastructure.datasource.retention.RetentionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static library.domain.model.reservation.ReservationStatus.消込済;
import static library.domain.model.reservation.ReservationStatus.準備完了;
import static library.domain.model.retention.availability.RetentionAvailability.取置不可;
import static library.domain.model.retention.availability.RetentionAvailability.取置可能;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void 未準備の予約を一覧できる() {
        ReservationRequest reservationRequest = new ReservationRequest(new MemberNumber(1), new EntryNumber(2));
        reservationRecordService.reserve(reservationRequest);

        ReservationWithWaitingOrderList 未準備の予約一覧 = retentionScenario.未準備の予約一覧();

        assertAll(
                () -> assertEquals(1, 未準備の予約一覧.asList().size()),
                () -> assertTrue(未準備の予約一覧.asList().get(0).memberNumber().sameValue(new MemberNumber(1))),
                () -> assertTrue(未準備の予約一覧.asList().get(0).entryNumber().sameValue(new EntryNumber(2)))
        );
    }

    @Test
    void 在庫がある未準備の予約が取置可能であることがわかる() {
        ReservationRequest reservationRequest = new ReservationRequest(new MemberNumber(1), new EntryNumber(2));
        reservationRecordService.reserve(reservationRequest);

        ReservationWithWaitingOrder 未準備の予約 = retentionScenario.未準備の予約一覧().asList().get(0);

        assertEquals(取置可能, 未準備の予約.retentionAvailability());
    }

    @Test
    void 在庫がない未準備の予約が取置不可であることがわかる() {
        ReservationRequest reservationRequest = new ReservationRequest(new MemberNumber(1), new EntryNumber(4));
        reservationRecordService.reserve(reservationRequest);

        ReservationRequest reservationRequest2 = new ReservationRequest(new MemberNumber(2), new EntryNumber(4));
        reservationRecordService.reserve(reservationRequest2);

        ReservationWithWaitingOrder 未準備の予約 = retentionScenario.未準備の予約一覧().asList().get(1);

        assertEquals(取置不可, 未準備の予約.retentionAvailability());
    }

    @Test
    void 未準備の予約所蔵品を取り置くことができる() {
        ReservationRequest reservationRequest = new ReservationRequest(new MemberNumber(1), new EntryNumber(2));
        reservationRecordService.reserve(reservationRequest);

        ItemNumber itemNumber = new ItemNumber("2-A");
        ReservationNumber reservationNumber = retentionScenario.未準備の予約一覧().asList().get(0).reservationNumber();
        Retention 未準備の予約された所蔵品 = new Retention(reservationNumber, itemNumber);
        retentionScenario.retain(未準備の予約された所蔵品);

        Retained 取置資料 = retentionDatasource.findBy(itemNumber);
        ReservationStatus 予約の状態 = reservationQueryService.reservationStatus(reservationNumber);
        ItemStatus 所蔵品の状態 = itemQueryService.status(itemNumber);

        assertAll(
                () -> assertTrue(取置資料.reservationNumber().sameValue(reservationNumber)),
                () -> assertTrue(取置資料.memberNumber().sameValue(new MemberNumber(1))),
                () -> assertEquals(LocalDate.now().toString(), 取置資料.retainedDate().toString()),
                () -> assertEquals(準備完了, 予約の状態),
                () -> assertEquals(ItemStatus.取置中, 所蔵品の状態)
        );
    }

    @Test
    void 取置中の所蔵品を予約者に貸し出すことができる() {
        MemberNumber memberNumber = new MemberNumber(1);
        ItemNumber itemNumber = new ItemNumber("2-A");

        // 予約
        ReservationRequest reservationRequest = new ReservationRequest(memberNumber, new EntryNumber(2));
        reservationRecordService.reserve(reservationRequest);

        ReservationNumber reservationNumber = retentionScenario.未準備の予約一覧().asList().get(0).reservationNumber();
        Retention 未準備の予約された所蔵品 = new Retention(reservationNumber, itemNumber);
        retentionScenario.retain(未準備の予約された所蔵品);

        // 貸出
        retentionScenario.loan(itemNumber);

        Loan loan = loanQueryService.findBy(itemNumber);
        ReservationStatus 予約の状態 = reservationQueryService.reservationStatus(reservationNumber);
        ItemStatus 所蔵品の状態 = itemQueryService.status(itemNumber);
        boolean 取置有無 = retentionMapper.exists準備完了(reservationNumber);
        boolean 取置の解放履歴有無 = retentionMapper.exists取置解放履歴(reservationNumber, itemNumber);

        assertAll(
                () -> assertTrue(memberNumber.sameValue(loan.memberNumber())),
                () -> assertTrue(itemNumber.sameValue(loan.item().所蔵品番号())),
                () -> assertTrue(LoanDate.now().sameValue(loan.loanDate())),
                () -> assertEquals(所蔵品の状態, ItemStatus.貸出中),
                () -> assertFalse(取置有無),
                () -> assertTrue(取置の解放履歴有無),
                () -> assertEquals(消込済, 予約の状態),
                () -> assertNull(reservationQueryService.reservationOf(reservationNumber)));
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