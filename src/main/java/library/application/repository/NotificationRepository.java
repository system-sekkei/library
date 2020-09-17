package library.application.repository;

import library.domain.model.loan.Loan;

/**
 * 通知リポジトリ
 */
public interface NotificationRepository {
    void expired(Loan loan);
}
