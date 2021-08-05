package library.domain.model.reservation.request;

import library.domain.model.material.Material;
import library.domain.model.material.MaterialNumber;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;

/**
 * *貸出予約
 */
public class Reservation {
    ReservationNumber reservationNumber;
    Member member;
    Material material;

    @Deprecated
    Reservation() {
    }

    private Reservation(ReservationNumber reservationNumber, Member member, Material material) {
        this.reservationNumber = reservationNumber;
        this.member = member;
        this.material = material;
    }

    public static Reservation of(Member member, Material material) {
        return new Reservation(ReservationNumber.generate(), member, material);
    }

    public Material material() {
        return material;
    }

    public Member member() {
        return member;
    }

    public MemberNumber memberNumber() {
        return member.number();
    }
    public MaterialNumber materialNumber() {
        return material.materialNumber();
    }
    public String showMaterial() {
        return material.show();
    }

    public ReservationNumber number() {
        return reservationNumber;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationNumber=" + reservationNumber +
                ", member=" + member +
                ", material=" + material +
                '}';
    }
}
