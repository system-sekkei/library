package library.application.service.loan;

import library.domain.model.loan.Loan;
import library.domain.model.loan.rule.LoanStatus;
import library.domain.model.material.collection.ItemNumber;
import library.domain.model.member.MemberNumber;
import org.springframework.stereotype.Service;

/**
 * 貸出記録の参照
 */
@Service
public class LoanQueryService {
    LoanRepository loanRepository;

    LoanQueryService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    /**
     * 会員の貸出を一覧する
     */
    public LoanStatus status(MemberNumber memberNumber) {
        return loanRepository.status(memberNumber);
    }

    /**
     * 貸出を見つける
     */
    public Loan findBy(ItemNumber itemNumber) {
        return loanRepository.findBy(itemNumber);
    }
}