package library.domain.model.material.instock;

/**
 * 在庫有無
 */
public enum InStock {
    在庫あり("〇"),
    在庫なし("×");

    String 表記;

    InStock(String 表記) {
        this.表記 = 表記;
    }
    public static InStock 在庫有無(StockQuantity 在庫数) {
        if (在庫数.value > 0) return 在庫あり;
        return 在庫なし;
    }

    public String show() {
        return 表記;
    }
}
