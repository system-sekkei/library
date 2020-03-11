package library.application.repository;

import library.domain.model.holding.HoldingCode;
import library.domain.model.holding.HoldingInStock;
import library.domain.model.holding.HoldingOnLoan;

/**
 * 蔵書リポジトリ
 */
public interface HoldingRepository {
    HoldingOnLoan findHoldingOnLoan(HoldingCode holdingCode);

    HoldingInStock findHoldingInStock(HoldingCode holdingCode);
}
