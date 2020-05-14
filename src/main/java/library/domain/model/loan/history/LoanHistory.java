package library.domain.model.loan.history;

import library.domain.model.book.item.ItemNumber;

/**
 * 貸出履歴
 */
public class LoanHistory {
    ItemNumber itemNumber;
    loanRecords loanRecords;
    ReturnRecords returnRecords;

    public LoanHistory(ItemNumber itemNumber, loanRecords loanRecords, ReturnRecords returnRecords) {
        this.itemNumber = itemNumber;
        this.loanRecords = loanRecords;
        this.returnRecords = returnRecords;
    }

    public boolean isLoaning() {
        return loanRecords.historyCount().minus(returnRecords.historyCount()) >= 1;
    }

    public boolean isStocked() {
        return !isLoaning();
    }
}
