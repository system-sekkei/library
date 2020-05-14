package library.domain.model.loan.history;

import library.domain.model.book.item.ItemNumber;

/**
 * 貸出履歴
 */
public class LoanHistory {
    ItemNumber itemNumber;
    LoanRecords loanRecords;
    ReturnRecords returnRecords;

    public LoanHistory(ItemNumber itemNumber, LoanRecords loanRecords, ReturnRecords returnRecords) {
        this.itemNumber = itemNumber;
        this.loanRecords = loanRecords;
        this.returnRecords = returnRecords;
    }

    // TODO 時系列として最後が貸出か返却かで判断したい　貸出記録の最後に日付と返却記録の最後の日付を比較：記録ない場合は貸出中ではない
    public boolean isLoaning() {
        return loanRecords.historyCount().minus(returnRecords.historyCount()) >= 1;
    }

    public boolean isStocked() {
        return !isLoaning();
    }
}
