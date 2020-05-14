package library.domain.model.loan.history;

import java.util.List;

/**
 * 返却履歴
 */
public class ReturnRecords {
    List<ReturnRecord> returnRecords;

    public ReturnRecords(List<ReturnRecord> returnRecords) {
        this.returnRecords = returnRecords;
    }

    HistoryCount historyCount() {
        return new HistoryCount(returnRecords.size());
    }
}
