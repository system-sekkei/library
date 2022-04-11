package library.presentation.api;

import library.application.service.loan.LoanExpiredCheckService;
import library.application.service.loan.LoanQueryService;
import library.domain.model.loan.Loan;
import library.domain.model.material.item.ItemNumber;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 期限切れチェック
 */
@RestController
@RequestMapping("/expired")
public class ExpireCheck {

    LoanQueryService loanQueryService;
    LoanExpiredCheckService loanExpiredCheckService;

    public ExpireCheck(LoanQueryService loanQueryService, LoanExpiredCheckService loanExpiredCheckService) {
        this.loanQueryService = loanQueryService;
        this.loanExpiredCheckService = loanExpiredCheckService;
    }

    @GetMapping
    String checkExpired(ItemNumber itemNumber) {
        Loan loan = loanQueryService.findBy(itemNumber);
        loanExpiredCheckService.expiredCheck(loan);
        return "nothing";
    }
}
