package library.application.scenario.reservation;

import library.ScenarioTest;
import library.application.scenario.retention.RetentionScenario;
import library.application.scenario.returns.ReturnsScenario;
import library.application.service.material.MaterialQueryService;
import library.application.service.member.MemberQueryService;
import library.application.service.reservation.ReservationQueryService;
import library.application.service.reservation.ReservationRecordService;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.ReservationNumber;
import library.domain.model.reservation.availability.ReservationAvailability;
import library.domain.model.reservation.request.ReservationRequest;
import library.domain.model.retention.Retention;
import library.domain.model.returned.ReturnDate;
import library.domain.model.returned.Returned;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static library.domain.model.reservation.availability.ReservationAvailability.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ScenarioTest
@Disabled
public class ReservationFlowTest {
    @Autowired
    ReservationScenario reservationScenario;

    @Autowired
    ReservationRecordService reservationRecordService;

    @Autowired
    ReservationQueryService reservationQueryService;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    MaterialQueryService materialQueryService;

    @Autowired
    ReturnsScenario returnsScenario;

    @Autowired
    RetentionScenario retentionScenario;

    @Autowired
    ReservationCancellationScenario reservationCancellationScenario;

    @Test
    @Disabled
    void 所蔵品目を一人１５点まで予約をすることができる() {
        List<Integer> entryNumbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);

        entryNumbers.forEach(entryNumber -> {
            reservationRecordService.reserve(new ReservationRequest(new MemberNumber(3), new EntryNumber(entryNumber)));
        });

        ReservationAvailability reservationAvailability = reservationScenario.reservationAvailability(new ReservationRequest(new MemberNumber(3), new EntryNumber(15)));

        assertEquals(予約可能, reservationAvailability);

        for (int code : entryNumbers) {
            予約キャンセル(code);
        }
    }

    @Test
    @Disabled
    void 一人１５点を超える点数を予約することはできない() {
        List<Integer> entryNumbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);

        entryNumbers.forEach(entryNumber -> {
            reservationRecordService.reserve(new ReservationRequest(new MemberNumber(4), new EntryNumber(entryNumber)));
        });

        ReservationAvailability reservationAvailability = reservationScenario.reservationAvailability(new ReservationRequest(new MemberNumber(4), new EntryNumber(19)));

        assertEquals(冊数制限により予約不可, reservationAvailability);

        for (int code : entryNumbers) {
            予約キャンセル(code);
        }
    }

    @Test
    @Disabled
    void 視聴覚資料を５点まで予約することができる() {
        List<Integer> entryNumbers = List.of(11, 12, 13, 14);

        entryNumbers.forEach(entryNumber -> {
            reservationRecordService.reserve(new ReservationRequest(new MemberNumber(5), new EntryNumber(entryNumber)));
        });

        ReservationAvailability reservationAvailability = reservationScenario.reservationAvailability(new ReservationRequest(new MemberNumber(5), new EntryNumber(15)));

        assertEquals(予約可能, reservationAvailability);

        for (int code : entryNumbers) {
            予約キャンセル(code);
        }
    }

    @Test
    @Disabled
    void 一人５点を超える視聴覚資料を予約することはできない() {
        List<Integer> entryNumbers = List.of(11, 12, 13, 14, 15);

        entryNumbers.forEach(entryNumber -> {
            reservationRecordService.reserve(new ReservationRequest(new MemberNumber(6), new EntryNumber(entryNumber)));
        });

        ReservationAvailability reservationAvailability = reservationScenario.reservationAvailability(new ReservationRequest(new MemberNumber(6), new EntryNumber(16)));

        assertEquals(視聴覚資料予約不可, reservationAvailability);

        for (int code : entryNumbers) {
            予約キャンセル(code);
        }

    }

    @Test
    @Disabled
    void 予約資料を予約者が受け取ったら予約履歴が削除される() {

    }

    @Test
    @Disabled
    void 貸出中の資料を予約することができる() {

    }

    @Test
    @Disabled
    void 取置期限内に受け取らなかった予約が無効になる() {
    }

    @Test
    @Disabled
    void 予約を取り消すことができる() {

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

    private void 予約キャンセル(int reservationNumber) {
        reservationCancellationScenario.cancel(new ReservationNumber(String.valueOf(reservationNumber)));
    }
}
