package library.domain.model.loan.history;

import java.util.List;

/**
 * 返却履歴
 */
public class ReturningHistory {
    List<ReturningRecord> returningRecords;

    public ReturningHistory(List<ReturningRecord> returningRecords) {
        this.returningRecords = returningRecords;
    }

    HistoryCount historyCount() {
        return new HistoryCount(returningRecords.size());
    }
}
