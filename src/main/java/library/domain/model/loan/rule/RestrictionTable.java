package library.domain.model.loan.rule;

import library.domain.model.loan.loan.DelayStatus;
import library.domain.model.member.MemberType;

import java.util.EnumMap;
import java.util.Map;

import static library.domain.model.loan.loan.DelayStatus.*;
import static library.domain.model.loan.rule.RestrictionOfQuantity.*;
import static library.domain.model.member.MemberType.*;

/**
 * 貸出制限の表条件
 */
class RestrictionTable {

    Map<DelayStatus, Map<MemberType, RestrictionOfQuantity>> table = new EnumMap<>(DelayStatus.class);

    {
        define(遅延日数３日未満, 大人, 貸出５冊まで);
        define(遅延日数３日未満, 子供, 貸出７冊まで);

        define(遅延日数７日未満, 大人, 貸出不可);
        define(遅延日数７日未満, 子供, 貸出４冊まで);

        define(それ以外, 大人, 貸出不可);
        define(それ以外, 子供, 貸出不可);
    }

    void define(DelayStatus delayStatus, MemberType memberType, RestrictionOfQuantity restrictionOfQuantity) {
        Map<MemberType, RestrictionOfQuantity> subMap = table.get(delayStatus);
        if(subMap == null) {
            subMap = new EnumMap<>(MemberType.class);
        }
        subMap.put(memberType, restrictionOfQuantity);
        table.put(delayStatus, subMap);
    }

    RestrictionOfQuantity lookup(DelayStatus delayStatus, MemberType memberType) {
        return table.get(delayStatus).get(memberType);
    }
}