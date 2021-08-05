package library.application.service.reservation;

import library.LibraryDBTest;
import library.application.service.material.MaterialQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.material.bibliography.Keyword;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.loanability.MaterialLoanability;
import library.domain.model.reservation.request.Reservation;
import library.domain.model.reservation.request.Reservations;
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
        Member member = memberQueryService.findMember(new MemberNumber(1));
        MaterialLoanability material = materialQueryService.search(new Keyword("ハンドブック")).asList().get(0);
        Reservation tryingToReserveMaterial = Reservation.of(member, material.material());
        reservationRecordService.reserve(tryingToReserveMaterial);

        Reservations reservations = reservationQueryService.reservations();

        assertAll(
                () -> assertEquals("1件", reservations.numberOfReservation().toString()));
    }
}