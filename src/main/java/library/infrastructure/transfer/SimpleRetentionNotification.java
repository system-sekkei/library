package library.infrastructure.transfer;

import library.application.service.retention.RetentionNotification;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.request.Reservation;
import library.domain.model.retention.Retained;
import org.springframework.stereotype.Component;

@Component
public class SimpleRetentionNotification implements RetentionNotification {
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
        System.out.println("通知宛先：" + memberNumber);
        System.out.println(message);
    }
}
