package library.domain.model.loan.rule;

import library.domain.model.delay.DelayStatus;

import java.util.HashMap;
import java.util.Map;

import static library.domain.model.delay.DelayStatus.*;
import static library.domain.model.loan.rule.Loanability.*;

/**
 * *貸出制限の表条件
 */
class RestrictionMap {

    Map<DelayStatus, Loanability> map = new HashMap<>();

    {
        define(遅延日数１５日未満, 貸出可能);
        define(遅延日数２ヶ月未満, 新規貸出不可);
        define(遅延日数２ヶ月以上, 貸出一定期間停止);
    }

    void define(DelayStatus delayStatus, Loanability loanbility) {
        map.put(delayStatus, loanbility);
    }

    Loanability of(DelayStatus delayStatus) {
        return map.get(delayStatus);
    }
}