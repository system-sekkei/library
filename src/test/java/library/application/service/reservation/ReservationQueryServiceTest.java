package library.application.service.reservation;

import library.LibraryDBTest;
import library.application.service.material.MaterialQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.request.ReservationRequest;
import library.domain.model.retention.wait.ReservationWithWaitingOrderList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@LibraryDBTest
class ReservationQueryServiceTest {
    @Autowired
    ReservationQueryService reservationQueryService;

    @Autowired
    ReservationRecordService reservationRecordService;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    MaterialQueryService materialQueryService;

    @Test
    void 予約を一覧できる() {
        ReservationRequest reservationRequest = new ReservationRequest(new MemberNumber(1), new EntryNumber(2));
        reservationRecordService.reserve(reservationRequest);

        ReservationWithWaitingOrderList reservations = reservationQueryService.未準備の予約一覧();

        assertAll(
                () -> assertEquals("1件", reservations.numberOfReservation().toString()));
    }
}