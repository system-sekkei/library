package library.domain.model.reservation;

import library.domain.model.bookcollection.SameBooksBookCollections;
import library.domain.model.member.Member;

/**
 * 貸出予約
 */
public class Reservation {
    Member member;
    SameBooksBookCollections sameBooksBookCollections;
    RetentionStatus retentionStatus;

    @Deprecated
    Reservation() {
    }

    public Reservation(Member member, SameBooksBookCollections sameBooksBookCollections, RetentionStatus retentionStatus) {
        this.member = member;
        this.sameBooksBookCollections = sameBooksBookCollections;
        this.retentionStatus = retentionStatus;
    }

    public Member member() {
        return member;
    }

    public SameBooksBookCollections sameBooksBookCollections() {
        return sameBooksBookCollections;
    }

    public RetentionStatus retentionStatus() {
        return retentionStatus;
    }
}
