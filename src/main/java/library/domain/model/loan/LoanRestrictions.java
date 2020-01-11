package library.domain.model.loan;

import library.domain.model.member.MemberType;

/**
 * 貸出制限
 */
public enum LoanRestrictions {
    貸出５冊まで,
    貸出７冊まで,
    貸出４冊まで,
    貸出不可;

    public static LoanRestrictions from(DelayPeriod delayPeriod, MemberType memberType) {
        if (memberType == MemberType.大人 && delayPeriod == DelayPeriod.遅延日数３日未満) {
            return 貸出５冊まで;
        }

        if (memberType == MemberType.子供) {
            if (delayPeriod == DelayPeriod.遅延日数３日未満) {
                return 貸出７冊まで;
            }

            if (delayPeriod == DelayPeriod.遅延日数７日未満) {
                return 貸出４冊まで;
            }
        }

        return 貸出不可;
    }
}
