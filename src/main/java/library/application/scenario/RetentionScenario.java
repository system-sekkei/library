package library.application.scenario;

import library.application.service.item.ItemQueryService;
import library.application.service.loan.LoanRecordService;
import library.application.service.reservation.ReservationQueryService;
import library.application.service.reservation.ReservationRecordService;
import library.application.service.retention.RetentionQueryService;
import library.application.service.retention.RetentionRecordService;
import library.domain.model.loan.LoanDate;
import library.domain.model.loan.LoanRequest;
import library.domain.model.material.item.Item;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.material.item.ItemStatus;
import library.domain.model.reservation.request.Reservation;
import library.domain.model.reservation.request.ReservationNumber;
import library.domain.model.reservation.request.UnpreparedReservations;
import library.domain.model.retention.MaterialMatching;
import library.domain.model.retention.Retained;
import library.domain.model.retention.RetainedList;
import library.domain.model.retention.Retention;
import org.springframework.stereotype.Service;

/**
 * 取置シナリオ
 */
@Service
public class RetentionScenario {
    ReservationQueryService reservationQueryService;
    ReservationRecordService reservationRecordService;
    RetentionQueryService retentionQueryService;
    RetentionRecordService retentionRecordService;
    ItemQueryService itemQueryService;
    LoanRecordService loanRecordService;

    public RetentionScenario(ReservationQueryService reservationQueryService, ReservationRecordService reservationRecordService, RetentionQueryService retentionQueryService, RetentionRecordService retentionRecordService, ItemQueryService itemQueryService, LoanRecordService loanRecordService) {
        this.reservationQueryService = reservationQueryService;
        this.reservationRecordService = reservationRecordService;
        this.retentionQueryService = retentionQueryService;
        this.retentionRecordService = retentionRecordService;
        this.itemQueryService = itemQueryService;
        this.loanRecordService = loanRecordService;
    }

    /**
     * 未準備の予約を一覧する
     */
    public UnpreparedReservations 未準備の予約一覧() {
        return reservationQueryService.未準備の予約一覧();
    }

    /**
     * 予約を見つける
     */
    public Reservation reservationOf(ReservationNumber reservationNumber) {
        return reservationQueryService.reservationOf(reservationNumber);
    }

    /**
     * 予約された本であることを確認する
     */
    public MaterialMatching isSameMaterial(Reservation reservation, Retention retention) {
        Item item = itemQueryService.findBy(retention.itemNumber());
        return retentionRecordService.materialMatching(reservation, item);
    }
    /**
     * 所蔵品の状態を確認する
     */
    public ItemStatus itemStatus(ItemNumber itemNumber) {
        return itemQueryService.status(itemNumber);
    }
    /**
     * 取り置く
     */
    public void retain(Retention retention) {
        retentionRecordService.retain(retention);
    }

    /**
     * 準備完了を一覧する
     */
    public RetainedList retainedList() {
        return retentionQueryService.retentions();
    }

    /**
     * 取り置いた本を貸し出す
     */
    public void loan(ItemNumber itemNumber) {
        // 貸出の実行
        Retained retained = retentionQueryService.findBy(itemNumber);
        LoanRequest loanRequest = retained.toLoanRequest(LoanDate.now());
        loanRecordService.loaned(loanRequest, retained);
    }

    /**
     * 取置の期限切れ
     */
    public void expire(ItemNumber itemNumber) {
        retentionRecordService.releaseAndExpire(itemNumber);
    }

    /**
     * 予約の取り消し
     */
    public void cancel(ReservationNumber reservationNumber) {
        Reservation reservation = reservationQueryService.reservationOf(reservationNumber);
        reservationRecordService.cancel(reservation);
    }
}
