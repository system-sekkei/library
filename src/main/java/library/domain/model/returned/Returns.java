package library.domain.model.returned;

import java.util.List;

/**
 * 返却一覧
 */
public class Returns {
    List<Returned> list;

    public Returns(List<Returned> list) {
        this.list = list;
    }

    public int count() {
        return list.size();
    }
}
