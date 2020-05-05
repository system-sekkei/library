package library.domain.model.loan.history;

import java.util.List;

/**
 * 貸出履歴
 */
public class LoaningHistory {
    List<LoaningRecord> loaningRecords;

    public LoaningHistory(List<LoaningRecord> loaningRecords) {
        this.loaningRecords = loaningRecords;
    }

    HistoryCount historyCount() {
        return new HistoryCount(loaningRecords.size());
    }
}
