package library.domain.model.loan.rule;

import library.domain.model.delay.DelayStatus;

import java.util.HashMap;
import java.util.Map;

import static library.domain.model.delay.DelayStatus.*;
import static library.domain.model.loan.rule.RestrictionOfDelay.*;

/**
 * *遅延状態による貸出制限の表条件
 */
class RestrictionOfDelayMap {

    Map<DelayStatus, RestrictionOfDelay> map = new HashMap<>();

    {
        define(遅延日数１５日未満, 貸出可能);
        define(遅延日数２ヶ月未満, 新規貸出不可);
        define(遅延日数２ヶ月以上, 貸出停止);
    }

    void define(DelayStatus delayStatus, RestrictionOfDelay restrictionOfDelay) {
        map.put(delayStatus, restrictionOfDelay);
    }

    RestrictionOfDelay of(DelayStatus delayStatus) {
        return map.get(delayStatus);
    }
}