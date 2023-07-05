package library.domain.model.reservation.wait;

import library.domain.model.material.entry.*;
import library.domain.model.material.instock.EntryInStock;
import library.domain.model.material.instock.StockQuantity;
import library.domain.model.reservation.availability.RetentionAvailability;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 貸出予約と待ち順番のテスト
 */
class ReservationWithWaitingOrderTest {
    Entry 予想どおりに不合理 = new Entry(new EntryNumber("7892"),
            new Title("予想どおりに不合理"),
            new WorkOf("ダン アリエリー"),
            EntryType.図書);
    @Test
    void 在庫数より待ち人数が少ない場合の取置可否を取得する() {
        ReservationWithWaitingOrder sut =
                new ReservationWithWaitingOrder(null, new EntryInStock(予想どおりに不合理, new StockQuantity(4)), new WaitingOrder(4));
        RetentionAvailability retentionAvailability = sut.retentionAvailability();
        assertEquals(RetentionAvailability.取置可能, retentionAvailability);
    }

    @Test
    void 在庫数より待ち人数が多い場合の取置可否を取得する() {
        ReservationWithWaitingOrder sut =
            new ReservationWithWaitingOrder(null, new EntryInStock(予想どおりに不合理, new StockQuantity(4)), new WaitingOrder(5));
        RetentionAvailability retentionAvailability = sut.retentionAvailability();
        assertEquals(RetentionAvailability.取置不可, retentionAvailability);
    }
}