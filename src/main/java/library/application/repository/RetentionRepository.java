package library.application.repository;

import library.domain.model.retention.RetainedHolding;

/**
 * 取置リポジトリ
 */
public interface RetentionRepository {

    void registerRetention(RetainedHolding retainedHolding);
}
