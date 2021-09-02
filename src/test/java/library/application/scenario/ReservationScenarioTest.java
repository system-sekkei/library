package library.application.scenario;

import library.LibraryDBTest;
import library.application.service.material.MaterialQueryService;
import library.application.service.member.MemberQueryService;
import library.application.service.reservation.ReservationQueryService;
import library.application.service.reservation.ReservationRecordService;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.request.Reservation;
import library.domain.model.reservation.request.ReservationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@LibraryDBTest
public class ReservationScenarioTest {
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
        ReservationRequest reservationRequest = new ReservationRequest(new MemberNumber(1), new EntryNumber(2));
        reservationRecordService.reserve(reservationRequest);

        Reservation reservation = reservationQueryService.reservations().asList().get(0);

        assertAll(
                () -> assertTrue(reservation.memberNumber().sameValue(reservationRequest.memberNumber())),
                () -> assertTrue(reservation.entryNumber().sameValue(reservationRequest.entryNumber()))
        );
    }

//    @Test
    void 所蔵品目を一人１５点まで予約をすることができる() {
    }

//    @Test
    void 一人１５点を超える点数を予約することはできない() {

    }

//    @Test
    void 視聴覚資料を５点まで予約することができる() {

    }

//    @Test
    void 一人５点を超える視聴覚資料を予約することはできない() {
    }

//    @Test
    void 貸出中の資料を予約することができる() {

    }

//    @Test
    void 取置期限内に受け取らなかった予約が無効になる() {
    }
}
