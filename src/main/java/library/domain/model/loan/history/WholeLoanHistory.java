package library.domain.model.loan.history;

import library.domain.model.item.Item;

import java.util.List;

/**
 * すべての貸出履歴
 */
public class WholeLoanHistory {
    List<LoanHistory> loanHistoryList;

    public WholeLoanHistory(List<LoanHistory> loanHistoryList) {
        this.loanHistoryList = loanHistoryList;
    }

    public static WholeLoanHistory empty() {
        return new WholeLoanHistory(List.of());
    }

    public LoanHistory findLibraryCard(Item item) {
        return loanHistoryList.stream()
            .filter(loanHistory -> loanHistory.itemNumber.sameValue(item.itemNumber()) )
            .findAny().orElseThrow(() -> new IllegalArgumentException("対象蔵書の貸出履歴が存在しません。 code:" + item.itemNumber()));
    }
}
