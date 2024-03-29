package library.application.service.returns;

import library.application.service.loan.LoanRepository;
import library.domain.model.returned.Returned;
import org.springframework.stereotype.Service;

/**
 * 返却の登録
 */
@Service
public class ReturnMaterialRecordService {
    LoanRepository loanRepository;

    ReturnMaterialRecordService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    /**
     * 返却を記録する
     */
    public void returned(Returned returned) {
        loanRepository.returned(returned);
    }
}
