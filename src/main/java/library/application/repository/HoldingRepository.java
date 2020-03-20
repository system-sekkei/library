package library.application.repository;

import library.domain.model.book.BookIds;
import library.domain.model.holding.HoldingCode;
import library.domain.model.holding.HoldingInStock;
import library.domain.model.holding.HoldingOnLoan;
import library.domain.model.holding.HoldingsInStock;

/**
 * 蔵書リポジトリ
 */
public interface HoldingRepository {
    HoldingOnLoan findHoldingOnLoan(HoldingCode holdingCode);

    HoldingInStock findHoldingInStock(HoldingCode holdingCode);

    HoldingsInStock findHoldingsInStockByBookIds(BookIds bookIds);
}
