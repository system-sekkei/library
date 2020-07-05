package library.domain.model.loan.rule;

import library.domain.model.loan.delay.DelayStatus;
import library.domain.model.member.MemberType;

import java.util.Objects;

/**
 * *遅延状況と会員種別（判定条件）
 */
public class DelayOfMember {
    DelayStatus delayStatus;
    MemberType memberType;

    public DelayOfMember(DelayStatus delayStatus, MemberType memberType) {
        this.delayStatus = delayStatus;
        this.memberType = memberType;
    }

    @Override
    public boolean equals(Object other) {
        DelayOfMember that = (DelayOfMember) other;
        return delayStatus == that.delayStatus &&
                memberType == that.memberType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(delayStatus, memberType);
    }
}
