package library.application.service.loan;

import library.application.repository.LoanRepository;
import library.domain.model.loan.loan.LoanRequest;
import org.springframework.stereotype.Service;

/**
 * 貸出記録サービス
 */
@Service
public class LoanRegisterService {
    LoanRepository loanRepository;

    LoanRegisterService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    /**
     * 貸出を記録する
     */
    public void loaned(LoanRequest loanRequest) {
        loanRepository.loan(loanRequest);
    }
}