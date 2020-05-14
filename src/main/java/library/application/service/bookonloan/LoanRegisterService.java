package library.application.service.bookonloan;

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
     * 貸出図書を登録する
     */
    public Loan registerLoan(LoanRequest loanRequest) {
        return loanRepository.registerLoan(loanRequest);
    }
}