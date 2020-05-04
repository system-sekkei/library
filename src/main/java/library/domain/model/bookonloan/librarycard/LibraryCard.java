package library.domain.model.bookonloan.librarycard;

import library.domain.model.item.ItemNumber;

/**
 * 図書カード
 */
public class LibraryCard {
    ItemNumber itemNumber;
    LoaningHistory loaningHistory;
    ReturningHistory returningHistory;

    public LibraryCard(ItemNumber itemNumber, LoaningHistory loaningHistory, ReturningHistory returningHistory) {
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
