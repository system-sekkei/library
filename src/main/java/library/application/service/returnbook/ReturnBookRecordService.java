package library.application.service.returnbook;

import library.application.repository.LoanRepository;
import library.domain.model.loan.loan.ReturningBookOnLoan;
import org.springframework.stereotype.Service;

/**
 * 貸出図書の返却登録サービス
 */
@Service
public class ReturnBookRecordService {
    LoanRepository loanRepository;

    ReturnBookRecordService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    /**
     * 貸出図書の返却を登録する
     */
    public void registerReturnBook(ReturningBookOnLoan returningBookOnLoan) {
        loanRepository.registerReturnBook(returningBookOnLoan);
    }
}
