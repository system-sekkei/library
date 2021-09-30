package library.application.scenario;

import library.application.service.item.ItemQueryService;
import library.application.service.loan.LoanQueryService;
import library.application.service.loan.LoanRecordService;
import library.application.service.member.MemberQueryService;
import library.application.service.returns.ReturnMaterialRecordService;
import library.domain.model.material.item.ItemLoanability;
import library.domain.model.loan.LoanRequest;
import library.domain.model.loan.rule.LoanStatus;
import library.domain.model.loan.rule.Loanability;
import library.domain.model.material.item.Item;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.material.item.ItemStatus;
import library.domain.model.material.item.ItemWithStatus;
import library.domain.model.member.MemberStatus;
import library.domain.model.retention.Retained;
import org.springframework.stereotype.Service;

/**
 * 貸出シナリオ
 */
@Service
public class LoanScenario {
    MemberQueryService memberQueryService;
    LoanQueryService loanQueryService;
    LoanRecordService loanRecordService;
    ReturnMaterialRecordService returnMaterialRecordService;
    ItemQueryService itemQueryService;

    public LoanScenario(MemberQueryService memberQueryService,
                        LoanQueryService loanQueryService,
                        LoanRecordService loanRecordService,
                        ReturnMaterialRecordService returnMaterialRecordService,
                        ItemQueryService itemQueryService) {
        this.memberQueryService = memberQueryService;
        this.loanQueryService = loanQueryService;
        this.loanRecordService = loanRecordService;
        this.returnMaterialRecordService = returnMaterialRecordService;
        this.itemQueryService = itemQueryService;
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
        Item 借りたい所蔵品 = itemQueryService.findBy(loanRequest.itemNumber());
        return loanStatus.貸出可否判定(借りたい所蔵品);
    }

    /**
     * 貸し出す
     */
    public void loan(LoanRequest loanRequest) {
        loanRecordService.loaned(loanRequest);
    }

    /**
     * 取り置いた所蔵品を貸し出す
     */
    public void loan(LoanRequest loanRequest, Retained retained) {
        loanRecordService.loaned(loanRequest, retained);
    }

    /**
     * 貸出状況を提示する
     */
    public LoanStatus loanStatus(LoanRequest loanRequest) {
        return loanQueryService.status(loanRequest.memberNumber());
    }

    public ItemLoanability 貸出可能な所蔵品かどうか(ItemNumber itemNumber) {
        ItemStatus 所蔵品の状態 = itemQueryService.status(itemNumber);
        Item 借りたい所蔵品 = itemQueryService.findBy(itemNumber);
        ItemWithStatus 借りたい所蔵品とその状態 = new ItemWithStatus(借りたい所蔵品, 所蔵品の状態);

        return 借りたい所蔵品とその状態.貸出可能かどうか();
    }
}
