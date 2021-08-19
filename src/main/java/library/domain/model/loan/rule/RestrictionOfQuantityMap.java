package library.domain.model.loan.rule;

import library.domain.model.member.MemberType;

import java.util.HashMap;
import java.util.Map;

import static library.domain.model.loan.rule.RestrictionOfQuantity.貸出１５点まで;
import static library.domain.model.loan.rule.RestrictionOfQuantity.貸出２０点まで;
import static library.domain.model.member.MemberType.中学生以上;
import static library.domain.model.member.MemberType.小学生以下;

/**
 * *貸出制限の表条件
 */
class RestrictionOfQuantityMap {

    Map<MemberType, RestrictionOfQuantity> map = new HashMap<>();

    {
        define(中学生以上, 貸出２０点まで);
        define(小学生以下, 貸出１５点まで);
    }

    void define(MemberType memberType, RestrictionOfQuantity restrictionOfQuantity) {
        map.put(memberType, restrictionOfQuantity);
    }

    RestrictionOfQuantity of(MemberType memberType) {
        return map.get(memberType);
    }
}