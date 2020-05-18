package library.application.coordinator.loan;

import library.application.service.item.ItemQueryService;
import library.application.service.loan.LoanQueryService;
import library.application.service.loan.LoanRegisterService;
import library.application.service.member.MemberQueryService;
import library.domain.model.loan.rule.LoanStatus;
import library.domain.model.loan.rule.Loanability;
import library.domain.model.loan.loan.LoanRequest;
import org.springframework.stereotype.Service;

import static library.domain.model.loan.rule.Loanability.貸出不可;

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
     * 会員番号の有効性を確認する
     */
    public boolean invalidMember(LoanRequest loanRequest) {
        return ! memberQueryService.exists(loanRequest.memberNumber());
    }

    /**
     * 貸出制限を判断する
     */
    public Loanability loanability(LoanRequest loanRequest) {
        LoanStatus loanStatus = loanQueryService.loanStatusOf(loanRequest.memberNumber());
        return loanStatus.shouldRestrict();
    }

    /**
     * 貸出を受け付ける
     */
    public void loan(LoanRequest loanRequest) {
        loanRegisterService.registerLoan(loanRequest);
    }

    /**
     * 貸出状況を提示する
     */
    public LoanStatus loanStatus(LoanRequest loanRequest) {
        return loanQueryService.loanStatusOf(loanRequest.memberNumber());
    }
}
