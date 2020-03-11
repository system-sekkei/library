package library.domain.model.retention;


import library.domain.model.book.Book;
import library.domain.model.holding.Holding;
import library.domain.model.holding.HoldingInStock;
import library.domain.model.holding.HoldingsInStock;

/**
 * 取置
 */
public class Retention {
    HoldingsInStock holdingsInStock;
    Retentions retentions;

    Retentionability retentionability(Book book) {
        for (HoldingInStock holdingInStock : holdingsInStock.list()) {
            if (holdingInStock.holding().sameBook(book) && retentionability(holdingInStock.holding())) {
                return Retentionability.取置可能;
            }
        }
        return Retentionability.取置不可能;
    }

    private boolean retentionability(Holding holding) {
        return retentions.notContains(holding);
    }

}
