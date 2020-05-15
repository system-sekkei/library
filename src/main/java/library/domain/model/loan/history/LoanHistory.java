package library.domain.model.loan.history;

import library.domain.model.book.item.ItemNumber;
import library.domain.model.loan.loan.Loans;
import library.domain.model.loan.returned.Returns;

/**
 * 貸出履歴
 */
public class LoanHistory {
    ItemNumber itemNumber;
    Loans loans;
    Returns returns;

    public LoanHistory(ItemNumber itemNumber, Loans loans, Returns returns) {
        this.itemNumber = itemNumber;
        this.loans = loans;
        this.returns = returns;
    }

    // TODO 時系列として最後が貸出か返却かで判断したい　貸出記録の最後に日付と返却記録の最後の日付を比較：記録ない場合は貸出中ではない
    public boolean isLoaning() {
        HistoryCount loanCount = new HistoryCount(loans.numberOfLoans().value());
        HistoryCount returnCount = returns.historyCount();
        return loanCount.minus(returnCount) >= 1;
    }

    public boolean isStocked() {
        return !isLoaning();
    }
}
