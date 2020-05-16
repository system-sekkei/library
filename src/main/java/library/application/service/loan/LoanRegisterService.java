package library.application.service.loan;

import library.application.repository.LoanRepository;
import library.domain.model.loan.loan.Loan;
import library.domain.model.loan.rule.LoanRequest;
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
     * 貸出を登録する
     */
    public void registerLoan(LoanRequest loanRequest) {
        loanRepository.registerLoan(loanRequest);
    }
}