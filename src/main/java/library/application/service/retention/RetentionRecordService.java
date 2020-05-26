package library.application.service.retention;

import library.application.repository.RetentionNotification;
import library.application.repository.RetentionRepository;
import library.domain.model.item.Item;
import library.domain.model.item.ItemNumber;
import library.domain.model.item.bibliography.Book;
import library.domain.model.reservation.Progress;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.retention.BookMatching;
import library.domain.model.reservation.retention.Retained;
import library.domain.model.reservation.retention.Retention;
import org.springframework.stereotype.Service;

import static library.domain.model.reservation.Progress.*;

/**
 * 取置登録サービス
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
    public BookMatching bookMatching(Reservation reservation, Item item) {
        Book requested = reservation.book();
        Book toRetain = item.book();
        return BookMatching.isSame(requested, toRetain);
    }
    /**
     * 予約された本を取り置く
     */
    public void retain(Retention retention) {

        ensure(未準備);
        retentionRepository.retained(retention);

        Retained retained = retentionRepository.findBy(retention.itemNumber());
        retentionNotification.retained(retained);
        ensure(準備完了);
    }

    /**
     * 取り置いた蔵書を貸し出す(準備完了を消しこむ)
     */
    public void releaseForLoan(ItemNumber itemNumber) {
        ensure(準備完了);
        Retained retained = retentionRepository.findBy(itemNumber);
        retentionRepository.released(retained);
        ensure(消込済);
    }

    /**
     * 取置を期限切れにする(準備完了を消し込む）
     */
    public void releaseAndExpire(ItemNumber itemNumber) {
        ensure(準備完了);
        Retained retained = retentionRepository.findBy(itemNumber);
        retentionRepository.released(retained);
        retentionRepository.expired(retained);
        ensure(消込済);
    }

    /**
     * 事前/事後の進捗状態を確認する
     */
    private void ensure(Progress progress) {
        // 進捗状態の表現の実験
        // TODO 状態の取得・判定・例外送出
        // 進捗という関心事の表現が目的
        // データの整合性のチェック目的ではない
    }

}
