package library.domain.model.loan.rule;

import library.domain.model.loan.delay.DelayStatus;
import library.domain.model.member.MemberType;

import java.util.HashMap;
import java.util.Map;

import static library.domain.model.loan.delay.DelayStatus.*;
import static library.domain.model.loan.rule.RestrictionOfQuantity.*;
import static library.domain.model.member.MemberType.*;

/**
 * *貸出制限の表条件
 */
class RestrictionMap {

    Map<DelayOfMember, RestrictionOfQuantity> map = new HashMap<>();

    {
        define(遅延日数１５日未満, 中学生以上, 貸出２０点まで);
        define(遅延日数１５日未満, 小学生以下, 貸出１５点まで);

        define(遅延日数２ヶ月未満, 中学生以上, 新規貸出不可);
        define(遅延日数２ヶ月未満, 小学生以下, 新規貸出不可);

        define(遅延日数２ヶ月以上, 中学生以上, 貸出予約停止);
        define(遅延日数２ヶ月以上, 小学生以下, 貸出予約停止);
    }

    void define(DelayStatus delayStatus, MemberType memberType, RestrictionOfQuantity restrictionOfQuantity) {
        map.put(new DelayOfMember(delayStatus,memberType), restrictionOfQuantity);
    }

    RestrictionOfQuantity of(DelayOfMember delayOfMember) {
        return map.get(delayOfMember);
    }
}