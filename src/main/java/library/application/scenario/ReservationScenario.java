package library.application.scenario;

import library.application.service.material.MaterialQueryService;
import library.application.service.member.MemberQueryService;
import library.application.service.reservation.ReservationRecordService;
import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.material.entry.Keyword;
import library.domain.model.member.MemberNumber;
import library.domain.model.member.MemberStatus;
import library.domain.model.reservation.loanability.MaterialLoanabilities;
import library.domain.model.reservation.request.ReservationRequest;
import org.springframework.stereotype.Service;

/**
 * 予約受付シナリオ
 */
@Service
public class ReservationScenario {
    ReservationRecordService reservationRecordService;
    MemberQueryService memberQueryService;
    MaterialQueryService materialQueryService;

    public ReservationScenario(ReservationRecordService reservationRecordService, MemberQueryService memberQueryService, MaterialQueryService materialQueryService) {
        this.reservationRecordService = reservationRecordService;
        this.memberQueryService = memberQueryService;
        this.materialQueryService = materialQueryService;
    }

    /**
     * 本を探す
     */
    public MaterialLoanabilities search(Keyword keyword) {
        return materialQueryService.search(keyword);
    }


    /**
     * 本を見つける
     */
    public Entry findMaterial(EntryNumber entryNumber) {
        return materialQueryService.findMaterial(entryNumber);
    }

    /**
     * 会員番号の有効性を確認する
     */
    public MemberStatus memberStatus(MemberNumber memberNumber) {
        return memberQueryService.status(memberNumber);
    }

    /**
     * 予約を記録する
     */
    public void reserve(Entry entry, MemberNumber memberNumber) {
        ReservationRequest reservationRequest = new ReservationRequest(memberNumber, entry.entryNumber());
        reservationRecordService.reserve(reservationRequest);
    }
}
