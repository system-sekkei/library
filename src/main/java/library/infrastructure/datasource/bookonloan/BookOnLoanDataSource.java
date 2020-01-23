package library.infrastructure.datasource.bookonloan;

import library.application.repository.BookOnLoanRepository;
import library.domain.model.bookonloan.BookOnLoanRegister;
import library.domain.model.bookonloan.BookOnLoans;
import org.springframework.stereotype.Repository;

@Repository
public class BookOnLoanDataSource implements BookOnLoanRepository {
    @Override
    public void registerBookOnLoan(BookOnLoanRegister bookOnLoanRegister) {

    }

    @Override
    public BookOnLoans findBookOnLoans() {
        return null;
    }
}
