package library.application.service.loan;

import library.domain.model.loan.Loan;
import library.domain.model.loan.due.DueDate;
import org.springframework.stereotype.Service;

/**
 * 貸出期限切れ確認サービス
 */
@Service
public class LoanExpiredCheckService {
    NotificationRepository notificationRepository;

    /**
     * 貸出期限切れを確認する
     */
    public void expiredCheck(Loan loan) {
        DueDate.貸出期限日(loan).status();
        notificationRepository.expired(loan);
    }
}
