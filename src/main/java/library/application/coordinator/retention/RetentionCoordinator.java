package library.application.coordinator.retention;

import library.application.service.item.ItemQueryService;
import library.application.service.loan.LoanRegisterService;
import library.application.service.reservation.ReservationQueryService;
import library.application.service.retention.RetentionQueryService;
import library.application.service.retention.RetentionRecordService;
import library.domain.model.item.Item;
import library.domain.model.item.ItemNumber;
import library.domain.model.item.ItemStatus;
import library.domain.model.loan.loan.LoanRequest;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.ReservationNumber;
import library.domain.model.reservation.reservation.Reservations;
import library.domain.model.reservation.retention.Retained;
import library.domain.model.reservation.retention.RetainedList;
import library.domain.model.reservation.retention.Retention;
import org.springframework.stereotype.Service;

/**
 * 取置業務
 */
@Service
public class RetentionCoordinator {
    ReservationQueryService reservationQueryService;
    RetentionQueryService retentionQueryService;
    RetentionRecordService retentionRecordService;
    ItemQueryService itemQueryService;
    LoanRegisterService loanRegisterService;

    public RetentionCoordinator(ReservationQueryService reservationQueryService, RetentionQueryService retentionQueryService, RetentionRecordService retentionRecordService, ItemQueryService itemQueryService, LoanRegisterService loanRegisterService) {
        this.reservationQueryService = reservationQueryService;
        this.retentionQueryService = retentionQueryService;
        this.retentionRecordService = retentionRecordService;
        this.itemQueryService = itemQueryService;
        this.loanRegisterService = loanRegisterService;
    }

    /**
     * 予約(取置依頼)を一覧する
     */
    public Reservations reservations() {
        return reservationQueryService.reservations();
    }

    /**
     * 予約(取置依頼)を取得する
     */
    public Reservation reservationOf(ReservationNumber reservationNumber) {
        return reservationQueryService.reservationOf(reservationNumber);
    }

    /**
     * 予約(取置依頼)と対応する蔵書であることを確認する
     */
    public boolean isSameBook(Reservation reservation, Retention retention) {
        Item item = itemQueryService.findBy(retention.itemNumber());
        return item.isSameBook(reservation.book());
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
        retentionRecordService.registerRetention(retention);
    }

    /**
     * 取置済を一覧する
     */
    public RetainedList retainedList() {
        return retentionQueryService.retentions();
    }

    /**
     * 取置済を貸し出す
     */
    public void loan(ItemNumber itemNumber) {
        Retained retained = retentionQueryService.findBy(itemNumber);
        retentionRecordService.loaned(itemNumber);

        // 貸出の実行
        LoanRequest loanRequest = retained.toLoanRequest();
        loanRegisterService.registerLoan(loanRequest);
    }
}
