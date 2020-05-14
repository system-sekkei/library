package library.domain.model.loan.history;

import java.util.List;

/**
 * 貸出履歴
 */
public class loanRecords {
    List<LoanRecord> loanRecords;

    public loanRecords(List<LoanRecord> loanRecords) {
        this.loanRecords = loanRecords;
    }

    HistoryCount historyCount() {
        return new HistoryCount(loanRecords.size());
    }
}
