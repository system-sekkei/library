package library.application.service.reservation;

import library.LibraryDBTest;
import library.application.service.material.MaterialQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.request.ReservationRequest;
import library.domain.model.reservation.unprepared.UnpreparedReservation;
import library.infrastructure.datasource.reservation.ReservationMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@LibraryDBTest
class ReservationRecordServiceTest {
    @Autowired
    ReservationRecordService reservationRecordService;

    @Autowired
    ReservationQueryService reservationQueryService;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    MaterialQueryService materialQueryService;

    @Autowired
    ReservationMapper reservationMapper;

    @Test
    void 貸出予約を登録することができる() {
        ReservationRequest reservationRequest = new ReservationRequest(new MemberNumber(1), new EntryNumber(2));
        reservationRecordService.reserve(reservationRequest);

        List<UnpreparedReservation> result = reservationMapper.select未準備の予約一覧();

        assertEquals(result.size(), 1);
    }

    @Test
    void 予約を取り消すことができる() {
        ReservationRequest reservationRequest = new ReservationRequest(new MemberNumber(1), new EntryNumber(2));
        reservationRecordService.reserve(reservationRequest);

        UnpreparedReservation reservation = reservationMapper.select未準備の予約一覧().get(0);

        reservationRecordService.cancel(reservation.reservation());

        List<UnpreparedReservation> reservations = reservationMapper.select未準備の予約一覧();

        assertTrue(reservations.stream().noneMatch(r -> r.number().value() == reservation.number().value()));
    }
}