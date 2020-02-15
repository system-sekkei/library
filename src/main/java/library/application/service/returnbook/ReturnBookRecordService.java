package library.application.service.returnbook;

import library.application.repository.BookOnLoanRepository;
import library.domain.model.bookonloan.ReturningBookOnLoan;
import org.springframework.stereotype.Service;

/**
 * 貸出図書の返却登録サービス
 */
@Service
public class ReturnBookRecordService {
    BookOnLoanRepository bookOnLoanRepository;

    ReturnBookRecordService(BookOnLoanRepository bookOnLoanRepository) {
        this.bookOnLoanRepository = bookOnLoanRepository;
    }

    public void registerReturnBook(ReturningBookOnLoan returningBookOnLoan) {
        bookOnLoanRepository.registerReturnBook(returningBookOnLoan);
    }
}
