package library.domain.model.bookonloan.librarycard;

/**
 * 図書カード
 */
public class LibraryCard {
    LoaningHistory loaningHistory;
    ReturningHistory returningHistory;

    public boolean isLoaning() {
        // TODO:
        return true;
    }
}
