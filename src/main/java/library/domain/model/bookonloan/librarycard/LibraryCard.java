package library.domain.model.bookonloan.librarycard;

import library.domain.model.holding.HoldingCode;

/**
 * 図書カード
 */
public class LibraryCard {
    HoldingCode holdingCode;
    LoaningHistory loaningHistory;
    ReturningHistory returningHistory;

    public LibraryCard(HoldingCode holdingCode, LoaningHistory loaningHistory, ReturningHistory returningHistory) {
        this.holdingCode = holdingCode;
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
