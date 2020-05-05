package library.application.repository;

import library.domain.model.book.item.ItemNumber;
import library.domain.model.book.item.HoldingInStock;
import library.domain.model.book.item.HoldingOnLoan;

/**
 * 蔵書リポジトリ
 */
public interface HoldingRepository {
    HoldingOnLoan findHoldingOnLoan(ItemNumber itemNumber);

    HoldingInStock findHoldingInStock(ItemNumber itemNumber);
}
