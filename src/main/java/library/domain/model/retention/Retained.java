package library.domain.model.retention;

import library.domain.model.loan.LoanDate;
import library.domain.model.loan.LoanRequest;
import library.domain.model.material.item.Item;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;

/**
 * 取置資料
 */
public class Retained {
    RetentionNumber retentionNumber;
    Member member;
    Item item;
    RetainedDate retainedDate;

    public boolean isExpired() {
        ExpireDate expireDate = ExpireDate.of(retainedDate);
        return expireDate.isExpired();
    }

    public String showExpireDate() {
        return ExpireDate.of(retainedDate).show();
    }

    public String showMaterial() {
        return item.所蔵品目().show();
    }

    public ItemNumber itemNumber() {
        return item.所蔵品番号();
    }

    public MemberNumber memberNumber() {
        return member.number();
    }

    public LoanRequest toLoanRequest(LoanDate loanDate) {
        return new LoanRequest(memberNumber(), itemNumber(), loanDate);
    }

    public RetainedDate retainedDate() {
        return retainedDate;
    }

    @Override
    public String toString() {
        return "Retained{" +
                "retentionNumber=" + retentionNumber +
                ", member=" + member +
                ", retainedDate=" + retainedDate +
                ", item=" + item +
                '}';
    }

    public RetentionNumber retentionNumber() {
        return retentionNumber;
    }
}
