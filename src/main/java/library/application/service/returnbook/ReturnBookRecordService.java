package library.application.service.returnbook;

import library.application.repository.BookOnLoanRepository;
import library.domain.model.bookonloan.BookOnLoan;
import library.domain.model.bookonloan.ReturnDate;
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

    public void registerReturnBook(BookOnLoan bookOnLoan, ReturnDate returnDate) {
        bookOnLoanRepository.registerReturnBook(bookOnLoan, returnDate);
    }
}
