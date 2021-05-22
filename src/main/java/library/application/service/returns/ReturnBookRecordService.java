package library.application.service.returns;

import library.application.service.loan.LoanRepository;
import library.domain.model.returned.Returned;
import org.springframework.stereotype.Service;

/**
 * 返却登録サービス
 */
@Service
public class ReturnBookRecordService {
    LoanRepository loanRepository;

    ReturnBookRecordService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    /**
     * 返却を記録する
     */
    public void returned(Returned returned) {
        loanRepository.returned(returned);
    }
}
