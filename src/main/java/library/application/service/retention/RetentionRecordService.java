package library.application.service.retention;

import library.domain.model.material.entry.Entry;
import library.domain.model.material.item.Item;
import library.domain.model.reservation.Reservation;
import library.domain.model.retention.MaterialMatching;
import library.domain.model.retention.Retained;
import library.domain.model.retention.Retention;
import library.domain.model.retention.RetentionNumber;
import org.springframework.stereotype.Service;

import static library.domain.model.reservation.ReservationStatus.未準備;
import static library.domain.model.reservation.ReservationStatus.消込済;

/**
 * 取置の登録
 */
@Service
public class RetentionRecordService {

    RetentionRepository retentionRepository;
    RetentionNotification retentionNotification;

    public RetentionRecordService(RetentionRepository retentionRepository, RetentionNotification retentionNotification) {
        this.retentionRepository = retentionRepository;
        this.retentionNotification = retentionNotification;
    }

    /**
     * 予約された本であることを確認する
     */
    public MaterialMatching materialMatching(Reservation reservation, Item item) {
        Entry requested = reservation.entry();
        Entry toRetain = item.所蔵品目();
        return MaterialMatching.isSame(requested, toRetain);
    }
    /**
     * 予約された本を取り置く
     */
    public void retain(Retention retention) {

        retentionRepository.ensureStatus(未準備);
        retentionRepository.retained(retention);

        Retained retained = retentionRepository.findBy(retention.itemNumber());
        retentionNotification.retained(retained);
//        ensure(準備完了);
    }

    /**
     * 取置を期限切れにする(準備完了を消し込む）
     */
    public void releaseAndExpire(RetentionNumber retentionNumber) {
//        ensure(準備完了);
        Retained retained = retentionRepository.findBy(retentionNumber);
        retentionRepository.expired(retained);
        retentionRepository.ensureStatus(消込済);
    }
}
