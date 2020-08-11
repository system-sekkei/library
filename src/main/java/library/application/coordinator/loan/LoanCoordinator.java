package library.application.coordinator.loan;

import library.application.service.loan.LoanQueryService;
import library.application.service.loan.LoanRecordService;
import library.application.service.member.MemberQueryService;
import library.application.service.returns.ReturnBookRecordService;
import library.domain.model.loan.LoanRequest;
import library.domain.model.loan.rule.LoanStatus;
import library.domain.model.loan.rule.Loanability;
import library.domain.model.member.MemberStatus;
import org.springframework.stereotype.Service;

/**
 * 貸出業務
 */
@Service
public class LoanCoordinator {
    MemberQueryService memberQueryService;
    LoanQueryService loanQueryService;
    LoanRecordService loanRecordService;
    ReturnBookRecordService returnBookRecordService;

    public LoanCoordinator(
            MemberQueryService memberQueryService,
            LoanQueryService loanQueryService,
            LoanRecordService loanRecordService,
            ReturnBookRecordService returnBookRecordService) {
        this.memberQueryService = memberQueryService;
        this.loanQueryService = loanQueryService;
        this.loanRecordService = loanRecordService;
        this.returnBookRecordService = returnBookRecordService;
    }

    /**
     * 会員番号の有効性を確認する
     */
    public MemberStatus memberStatus(LoanRequest loanRequest) {
        return memberQueryService.status(loanRequest.memberNumber());
    }

    /**
     * 貸出制限を判断する
     */
    public Loanability loanability(LoanRequest loanRequest) {
        LoanStatus loanStatus = loanQueryService.status(loanRequest.memberNumber());
        return loanStatus.shouldRestrict();
    }

    /**
     * 貸し出す
     */
    public void loan(LoanRequest loanRequest) {
        loanRecordService.loaned(loanRequest);
    }

    /**
     * 貸出状況を提示する
     */
    public LoanStatus loanStatus(LoanRequest loanRequest) {
        return loanQueryService.status(loanRequest.memberNumber());
    }
}
