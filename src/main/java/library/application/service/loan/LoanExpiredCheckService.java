package library.application.service.loan;

import library.application.repository.NotificationRepository;
import library.domain.model.loan.Loan;
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
        loan.dueDateStatus();
        notificationRepository.expired(loan);
    }
}
