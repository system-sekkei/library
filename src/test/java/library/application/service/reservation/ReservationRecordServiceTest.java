package library.application.service.reservation;

import library.LibraryDBTest;
import library.application.service.material.MaterialQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.material.entry.Keyword;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.loanability.MaterialLoanability;
import library.domain.model.reservation.request.Reservation;
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
        Member member = memberQueryService.findMember(new MemberNumber(1));
        MaterialLoanability material = materialQueryService.search(new Keyword("ハンドブック")).asList().get(0);

        Reservation tryingToReserveMaterial = Reservation.of(member, material.entry());
        reservationRecordService.reserve(tryingToReserveMaterial);

        List<Reservation> result = reservationMapper.selectAllReservation();

        assertEquals(result.size(), 1);
    }

    @Test
    void 予約を取り消すことができる() {
        Member member = memberQueryService.findMember(new MemberNumber(2));
        MaterialLoanability material = materialQueryService.search(new Keyword("ハンドブック")).asList().get(0);

        Reservation tryingToReserveMaterial = Reservation.of(member, material.entry());
        reservationRecordService.reserve(tryingToReserveMaterial);

        Reservation reservation = reservationMapper.selectAllReservation().get(0);

        reservationRecordService.cancel(reservation);

        List<Reservation> reservations = reservationMapper.selectAllReservation();

        assertTrue(reservations.stream().noneMatch(r -> r.number().value() == reservation.number().value()));
    }
}