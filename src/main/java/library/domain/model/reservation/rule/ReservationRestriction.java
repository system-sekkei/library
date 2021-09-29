package library.domain.model.reservation.rule;

import library.domain.model.material.entry.Entry;
import library.domain.model.member.Member;
import library.domain.model.reservation.availability.ReservationAvailability;
import library.domain.model.reservation.request.NumberOfReservation;
import library.domain.model.reservation.request.Reservations;

import static library.domain.model.material.entry.EntryType.視聴覚資料;
import static library.domain.model.reservation.availability.ReservationAvailability.*;

/**
 * 予約制限
 */
public class ReservationRestriction {
    Member member;
    Reservations reservations;

    NumberOfReservation 予約可能冊数上限 = new NumberOfReservation(15);
    NumberOfReservation 視聴覚資料の予約可能冊数上限 = new NumberOfReservation(5);

    public ReservationRestriction(Member member, Reservations reservations) {
        this.member = member;
        this.reservations = reservations;
    }

    public ReservationAvailability 予約可否判定(Entry 予約したい本) {
        if (予約したい本.所蔵品目種別() == 視聴覚資料) {
            if (視聴覚資料の予約可能冊数上限.より多い(reservations.視聴覚資料の冊数().足す(1))) {
                return 視聴覚資料予約不可;
            }
        }

        if (予約可能冊数上限.より多い(reservations.numberOfReservation().足す(1))) {
            return 冊数制限により予約不可;
        }

        return 予約可能;
    }
}
