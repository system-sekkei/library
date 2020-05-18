package library.application.coordinator.loan;

import library.application.service.item.ItemQueryService;
import library.application.service.loan.LoanQueryService;
import library.application.service.loan.LoanRegisterService;
import library.application.service.member.MemberQueryService;
import library.domain.model.loan.rule.LoanStatus;
import library.domain.model.loan.rule.RestrictionResult;
import library.domain.model.loan.loan.LoanRequest;
import org.springframework.stereotype.Service;

/**
 * 貸出コーディネーター
 */
@Service
public class LoanCoordinator {
    MemberQueryService memberQueryService;
    ItemQueryService itemQueryService;
    LoanQueryService loanQueryService;
    LoanRegisterService loanRegisterService;

    public LoanCoordinator(
            MemberQueryService memberQueryService,
            ItemQueryService itemQueryService,
            LoanQueryService loanQueryService,
            LoanRegisterService loanRegisterService) {
        this.memberQueryService = memberQueryService;
        this.itemQueryService = itemQueryService;
        this.loanQueryService = loanQueryService;
        this.loanRegisterService = loanRegisterService;
    }

    /**
     * 貸出制限を判断する
     */
    public RestrictionResult shouldRestrict(LoanRequest loanRequest) {
        LoanStatus loanStatus = loanQueryService.loanStatusOf(loanRequest.memberNumber());
        return loanStatus.shouldRestrict();
    }
    /**
     * 貸出を受付る
     */
    public void loan(LoanRequest loanRequest) {
        loanRegisterService.registerLoan(loanRequest);
    }

}
