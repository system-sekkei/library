package library.application.service.bookonloan;

import library.application.repository.BookOnLoanRepository;
import library.domain.model.bookonloan.LoaningOfBook;
import org.springframework.stereotype.Service;

/**
 * 貸出図書登録サービス
 */
@Service
public class BookOnLoanRecordService {
    BookOnLoanRepository bookOnLoanRepository;

    BookOnLoanRecordService(BookOnLoanRepository bookOnLoanRepository) {
        this.bookOnLoanRepository = bookOnLoanRepository;
    }

    public void registerBookOnLoan(LoaningOfBook loaningOfBook) {
        bookOnLoanRepository.registerBookOnLoan(loaningOfBook);
    }
}