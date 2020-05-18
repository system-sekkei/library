package library.application.service.loan;

import library.application.repository.LoanRepository;
import library.domain.model.loan.loan.Loan;
import library.domain.model.loan.rule.LoanStatus;
import library.domain.model.item.ItemNumber;
import library.domain.model.member.MemberNumber;
import org.springframework.stereotype.Service;

/**
 * 貸出参照サービス
 */
@Service
public class LoanQueryService {
    LoanRepository loanRepository;

    LoanQueryService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    /**
     * 会員の全貸出図書を一覧する
     */
    public LoanStatus loanStatusOf(MemberNumber memberNumber) {
        LoanStatus loanStatus = loanRepository.loanStatus(memberNumber);
        return loanStatus;
    }

    /**
     * 貸出を見つける
     */
    public Loan findLoanByItemNumber(ItemNumber itemNumber) {
        return loanRepository.findLoanByItemNumber(itemNumber);
    }
}