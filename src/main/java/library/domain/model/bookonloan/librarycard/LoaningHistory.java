package library.domain.model.bookonloan.librarycard;

import java.util.List;

/**
 * 貸出履歴
 */
public class LoaningHistory {
    List<LoaningRecord> loaningRecords;

    HistoryCount historyCount() {
        return new HistoryCount(loaningRecords.size());
    }
}
