package library.domain.model.holding;

import java.util.List;

/**
 * 貸出中の蔵書リスト
 */
public class HoldingsOnLoan {
    List<HoldingOnLoan> list;

    HoldingsOnLoan(List<HoldingOnLoan> list) {
        this.list = list;
    }
}
