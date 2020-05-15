package library.domain.model.loan.returned;

import library.domain.model.loan.history.HistoryCount;

import java.util.List;

/**
 * 返却一覧
 */
public class Returns {
    List<Returned> list;

    public Returns(List<Returned> list) {
        this.list = list;
    }

    public HistoryCount historyCount() {
        return new HistoryCount(list.size());
    }
}
