package library.application.service.bookonloan;

import library.application.repository.BookOnLoanRepository;
import library.domain.model.bookonloan.loan.BookOnLoan;
import library.domain.model.bookonloan.loaning.BookOnLoanRequest;
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
    public BookOnLoan registerBookOnLoan(BookOnLoanRequest bookOnLoanRequest) {
        return bookOnLoanRepository.registerBookOnLoan(bookOnLoanRequest);
    }
}