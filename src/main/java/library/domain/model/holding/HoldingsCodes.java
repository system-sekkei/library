package library.domain.model.holding;

import java.util.List;

/**
 * 蔵書コードリスト
 */
public class HoldingsCodes {
    List<HoldingCode> list;

    public HoldingsCodes(List<HoldingCode> list) {
        this.list = list;
    }

    public List<HoldingCode> asList() {
        return list;
    }
}
