package library.infrastructure.transfer;

import library.application.service.retention.RetentionNotification;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.Reservation;
import library.domain.model.retention.Retained;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SimpleRetentionNotification implements RetentionNotification {
    private static final Logger logger = LoggerFactory.getLogger(SimpleRetentionNotification.class);

    @Override
    public void retained(Retained retained) {
        String message = String.format(
                "予約いただいた本が準備できました。\n本：%s\n取置期限:%s",
                retained.showMaterial(),
                retained.showExpireDate()
        );
        notify(retained.memberNumber(), message);
    }

    @Override
    public void notAvailable(Reservation reservation) {
        String message = String.format(
                "予約いただいた本は在庫がありませんでした。\n本：%s",
                reservation.showMaterial()
        );
        notify(reservation.memberNumber(), message);

    }

    private void notify(MemberNumber memberNumber, String message) {
        logger.info("通知宛先:{} 内容:{}", memberNumber, message);
    }
}
