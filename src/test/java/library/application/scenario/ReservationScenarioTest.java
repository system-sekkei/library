package library.application.scenario;

import library.LibraryDBTest;
import library.application.service.material.MaterialQueryService;
import library.application.service.member.MemberQueryService;
import library.application.service.reservation.ReservationQueryService;
import library.application.service.reservation.ReservationRecordService;
import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.availability.ReservationAvailability;
import library.domain.model.reservation.request.ReservationRequest;
import library.domain.model.reservation.unprepared.UnpreparedReservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static library.domain.model.material.entry.EntryType.図書;
import static library.domain.model.reservation.availability.ReservationAvailability.*;
import static org.junit.jupiter.api.Assertions.*;

@LibraryDBTest
public class ReservationScenarioTest {
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

    @Test
    void 所蔵品目を予約をすることができる() {
        EntryNumber entryNumber = new EntryNumber(2);
        MemberNumber memberNumber = new MemberNumber(2);
        reservationScenario.reserve(new Entry(entryNumber, null, null, 図書), memberNumber);

        UnpreparedReservation reservation = reservationQueryService.未準備の予約一覧().asList().get(0);

        assertAll(
                () -> assertTrue(reservation.memberNumber().sameValue(memberNumber)),
                () -> assertTrue(reservation.entryNumber().sameValue(entryNumber))
        );
    }

    @Test
    void 所蔵品目を一人１５点まで予約をすることができる() {
        List<Integer> entryNumbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);

        entryNumbers.forEach(entryNumber -> {
            reservationRecordService.reserve(new ReservationRequest(new MemberNumber(2), new EntryNumber(entryNumber)));
        });

        ReservationAvailability reservationAvailability = reservationScenario.reservationAvailability(new ReservationRequest(new MemberNumber(2), new EntryNumber(15)));

        assertAll(
                () -> assertEquals(予約可能, reservationAvailability)
        );
    }

    @Test
    void 一人１５点を超える点数を予約することはできない() {
        List<Integer> entryNumbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);

        entryNumbers.forEach(entryNumber -> {
            reservationRecordService.reserve(new ReservationRequest(new MemberNumber(2), new EntryNumber(entryNumber)));
        });

        ReservationAvailability reservationAvailability = reservationScenario.reservationAvailability(new ReservationRequest(new MemberNumber(2), new EntryNumber(19)));

        assertAll(
                () -> assertEquals(冊数制限により予約不可, reservationAvailability)
        );
    }

    @Test
    void 視聴覚資料を５点まで予約することができる() {
        List<Integer> entryNumbers = List.of(11, 12, 13, 14);

        entryNumbers.forEach(entryNumber -> {
            reservationRecordService.reserve(new ReservationRequest(new MemberNumber(2), new EntryNumber(entryNumber)));
        });

        ReservationAvailability reservationAvailability = reservationScenario.reservationAvailability(new ReservationRequest(new MemberNumber(2), new EntryNumber(15)));

        assertAll(
                () -> assertEquals(予約可能, reservationAvailability)
        );
    }

    @Test
    void 一人５点を超える視聴覚資料を予約することはできない() {
        List<Integer> entryNumbers = List.of(11, 12, 13, 14, 15);

        entryNumbers.forEach(entryNumber -> {
            reservationRecordService.reserve(new ReservationRequest(new MemberNumber(2), new EntryNumber(entryNumber)));
        });

        ReservationAvailability reservationAvailability = reservationScenario.reservationAvailability(new ReservationRequest(new MemberNumber(2), new EntryNumber(16)));

        assertAll(
                () -> assertEquals(視聴覚資料予約不可, reservationAvailability)
        );
    }

    // @Test
    void 予約資料を予約者が受け取ったら予約履歴が削除される() {

    }

    //    @Test
    void 貸出中の資料を予約することができる() {

    }

//    @Test
    void 取置期限内に受け取らなかった予約が無効になる() {
    }

    // @Test
    void 予約を取り消すことができる() {

    }
}
