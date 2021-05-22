package library.application.service.loan;

import library.domain.model.loan.Loan;

/**
 * 通知リポジトリ
 */
public interface NotificationRepository {
    void expired(Loan loan);
}
