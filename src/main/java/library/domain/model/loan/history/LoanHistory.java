package library.domain.model.loan.history;

import library.domain.model.book.item.ItemNumber;

/**
 * 貸出履歴
 */
public class LoanHistory {
    ItemNumber itemNumber;
    LoaningHistory loaningHistory;
    ReturningHistory returningHistory;

    public LoanHistory(ItemNumber itemNumber, LoaningHistory loaningHistory, ReturningHistory returningHistory) {
        this.itemNumber = itemNumber;
        this.loaningHistory = loaningHistory;
        this.returningHistory = returningHistory;
    }

    public boolean isLoaning() {
        return loaningHistory.historyCount().minus(returningHistory.historyCount()) >= 1;
    }

    public boolean isStocked() {
        return !isLoaning();
    }
}
