package library.domain.model.reservation.request;

import library.domain.model.material.entry.EntryNumber;
import library.domain.model.member.MemberNumber;

import javax.validation.Valid;

/**
 * 予約依頼
 */
public class ReservationRequest {

    @Valid
    MemberNumber memberNumber;

    @Valid
    EntryNumber entryNumber;

    public ReservationRequest(MemberNumber memberNumber, EntryNumber entryNumber) {
        this.memberNumber = memberNumber;
        this.entryNumber = entryNumber;
    }

    @Deprecated
    ReservationRequest() {
    }
    public MemberNumber memberNumber() {
        return memberNumber;
    }

    public EntryNumber entryNumber() {
        return entryNumber;
    }

    public static ReservationRequest empty() {
        return new ReservationRequest();
    }

    @Override
    public String toString() {
        return "ReservationRequest{" +
                "memberNumber=" + memberNumber +
                ", entryNumber=" + entryNumber +
                '}';
    }
}
