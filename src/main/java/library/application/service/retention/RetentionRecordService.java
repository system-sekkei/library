package library.application.service.retention;

import library.application.repository.RetentionRepository;
import library.domain.model.item.ItemNumber;
import library.domain.model.reservation.reservation.ReservationNumber;
import library.domain.model.reservation.retention.Retention;
import org.springframework.stereotype.Service;

/**
 * 取置登録サービス
 */
@Service
public class RetentionRecordService {

    RetentionRepository retentionRepository;

    public RetentionRecordService(RetentionRepository retentionRepository) {
        this.retentionRepository = retentionRepository;
    }

    /**
     * 予約された本を取り置く
     */
    public void retain(Retention retention) {
        retentionRepository.retained(retention);
    }

    /**
     * 取り置いた蔵書を貸し出す(準備完了を消しこむ)
     */
    public void loan(ItemNumber itemNumber) {
        retentionRepository.loan(itemNumber);
    }

    /**
     * 期限切れにする
     */
    public void expire(ItemNumber itemNumber) {
        retentionRepository.expire(itemNumber);
    }

    public void cancel(ReservationNumber reservationNumber) {
        retentionRepository.cancel(reservationNumber);
    }
}
