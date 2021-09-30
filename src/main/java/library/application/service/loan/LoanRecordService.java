package library.application.service.loan;

import library.domain.model.loan.LoanRequest;
import library.domain.model.retention.Retained;
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

    /**
     * 取り置いた所蔵品を貸し出す
     */
    public void loaned(LoanRequest loanRequest, Retained retained) {
        loanRepository.loan(loanRequest, retained);
    }
}