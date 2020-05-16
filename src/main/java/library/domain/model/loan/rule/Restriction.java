package library.domain.model.loan.rule;

import library.domain.model.loan.loan.DelayStatus;
import library.domain.model.loan.loan.Loans;
import library.domain.model.member.Member;
import library.domain.model.member.MemberType;
import library.domain.type.date.CurrentDate;

/**
 * 貸出制限
 */
class Restriction {
    Member member;
    Loans loans;
    CurrentDate date;

    Restriction(Member member, Loans loans, CurrentDate date) {
        this.member = member;
        this.loans = loans;
        this.date = date;
    }

    RestrictionOfQuantity ofQuantity() {
        DelayStatus delayStatus = loans.worst(date);
        MemberType memberType = member.type();

        if (memberType == MemberType.大人 && delayStatus == DelayStatus.遅延日数３日未満) {
            return RestrictionOfQuantity.貸出５冊まで;
        }

        if (memberType == MemberType.子供) {
            if (delayStatus == DelayStatus.遅延日数３日未満) {
                return RestrictionOfQuantity.貸出７冊まで;
            }

            if (delayStatus == DelayStatus.遅延日数７日未満) {
                return RestrictionOfQuantity.貸出４冊まで;
            }
        }
        return RestrictionOfQuantity.貸出不可;
    }
}
