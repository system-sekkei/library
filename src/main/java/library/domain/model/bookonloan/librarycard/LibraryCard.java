package library.domain.model.bookonloan.librarycard;

import library.domain.model.holding.HoldingCode;

/**
 * 図書カード
 */
public class LibraryCard {
    HoldingCode holdingCode;
    LoaningHistory loaningHistory;
    ReturningHistory returningHistory;

    public boolean isLoaning() {
        // TODO:
        return true;
    }
}
