package library.application.service.returnbook;

import library.application.repository.BookOnLoanRepository;
import library.domain.model.loan.loan.ReturningBookOnLoan;
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

    /**
     * 貸出図書の返却を登録する
     */
    public void registerReturnBook(ReturningBookOnLoan returningBookOnLoan) {
        bookOnLoanRepository.registerReturnBook(returningBookOnLoan);
    }
}
