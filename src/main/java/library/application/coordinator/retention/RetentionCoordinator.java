package library.application.coordinator.retention;

import library.application.service.item.ItemQueryService;
import library.application.service.loan.LoanRecordService;
import library.application.service.reservation.ReservationQueryService;
import library.application.service.reservation.ReservationRecordService;
import library.application.service.retention.RetentionQueryService;
import library.application.service.retention.RetentionRecordService;
import library.domain.model.item.Item;
import library.domain.model.item.ItemNumber;
import library.domain.model.item.ItemStatus;
import library.domain.model.reservation.retention.*;
import library.domain.model.loan.LoanRequest;
import library.domain.model.reservation.request.Reservation;
import library.domain.model.reservation.request.ReservationNumber;
import library.domain.model.reservation.request.Reservations;
import org.springframework.stereotype.Service;

/**
 * 取置業務
 */
@Service
public class RetentionCoordinator {
    ReservationQueryService reservationQueryService;
    ReservationRecordService reservationRecordService;
    RetentionQueryService retentionQueryService;
    RetentionRecordService retentionRecordService;
    ItemQueryService itemQueryService;
    LoanRecordService loanRecordService;

    public RetentionCoordinator(ReservationQueryService reservationQueryService, ReservationRecordService reservationRecordService, RetentionQueryService retentionQueryService, RetentionRecordService retentionRecordService, ItemQueryService itemQueryService, LoanRecordService loanRecordService) {
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
    public Reservations reservations() {
        return reservationQueryService.reservations();
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
    public BookMatching isSameBook(Reservation reservation, Retention retention) {
        Item item = itemQueryService.findBy(retention.itemNumber());
        return retentionRecordService.bookMatching(reservation, item);
    }
    /**
     * 蔵書の状態を確認する
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
        LoanRequest loanRequest = retained.toLoanRequest();
        loanRecordService.loaned(loanRequest);

        retentionRecordService.releaseForLoan(itemNumber);
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
