package library.application.service.bookonloan;

import library.application.repository.BookOnLoanRepository;
import library.domain.model.bookonloan.loaning.LoaningOfBookCollection;
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

    /**
     * 貸出図書を登録
     */
    public void registerBookOnLoan(LoaningOfBookCollection loaningOfBookCollection) {
        bookOnLoanRepository.registerBookOnLoan(loaningOfBookCollection);
    }
}