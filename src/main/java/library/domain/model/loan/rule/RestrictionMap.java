package library.domain.model.loan.rule;

import library.domain.model.delay.DelayStatus;

import java.util.HashMap;
import java.util.Map;

import static library.domain.model.delay.DelayStatus.*;
import static library.domain.model.loan.rule.RestrictionOfLoanbility.*;

/**
 * *貸出制限の表条件
 */
class RestrictionMap {

    Map<DelayStatus, RestrictionOfLoanbility> map = new HashMap<>();

    {
        define(遅延日数１５日未満, 貸出可);
        define(遅延日数２ヶ月未満, 新規貸出不可);
        define(遅延日数２ヶ月以上, 貸出一定期間停止);
    }

    void define(DelayStatus delayStatus, RestrictionOfLoanbility restrictionOfLoanbility) {
        map.put(delayStatus, restrictionOfLoanbility);
    }

    RestrictionOfLoanbility of(DelayStatus delayStatus) {
        return map.get(delayStatus);
    }
}