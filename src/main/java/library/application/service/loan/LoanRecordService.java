package library.application.service.loan;

import library.domain.model.loan.LoanRequest;
import org.springframework.stereotype.Service;

/**
 * 貸出の記録
 */
@Service
public class LoanRecordService {
    LoanRepository loanRepository;

    LoanRecordService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    /**
     * 貸出を記録する
     */
    public void loaned(LoanRequest loanRequest) {
        loanRepository.loan(loanRequest);
    }
}