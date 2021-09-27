package library.application.scenario;

import library.LibraryDBTest;
import library.application.service.item.ItemQueryService;
import library.application.service.material.MaterialQueryService;
import library.application.service.member.MemberQueryService;
import library.application.service.reservation.ReservationQueryService;
import library.application.service.reservation.ReservationRecordService;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.material.item.ItemStatus;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.ReservationStatus;
import library.domain.model.reservation.request.Reservation;
import library.domain.model.reservation.request.ReservationNumber;
import library.domain.model.reservation.request.ReservationRequest;
import library.domain.model.reservation.request.Reservations;
import library.domain.model.retention.Retained;
import library.domain.model.retention.Retention;
import library.infrastructure.datasource.retention.RetentionDatasource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;

import java.time.LocalDate;

import static library.domain.model.reservation.ReservationStatus.準備完了;
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

    @Test
    void 未準備の予約を一覧できる() {
        ReservationRequest reservationRequest = new ReservationRequest(new MemberNumber(1), new EntryNumber(2));
        reservationRecordService.reserve(reservationRequest);

        Reservations 未準備の予約一覧 = retentionScenario.未準備の予約一覧();

        assertAll(
                () -> assertEquals(1, 未準備の予約一覧.asList().size()),
                () -> assertTrue(未準備の予約一覧.asList().get(0).memberNumber().sameValue(new MemberNumber(1))),
                () -> assertTrue(未準備の予約一覧.asList().get(0).entryNumber().sameValue(new EntryNumber(2)))
        );
    }

    @Test
    void 未準備の予約所蔵品を取り置くことができる() {
        ReservationRequest reservationRequest = new ReservationRequest(new MemberNumber(1), new EntryNumber(2));
        reservationRecordService.reserve(reservationRequest);

        ItemNumber itemNumber = new ItemNumber("2-A");
        ReservationNumber reservationNumber = retentionScenario.未準備の予約一覧().asList().get(0).number();
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