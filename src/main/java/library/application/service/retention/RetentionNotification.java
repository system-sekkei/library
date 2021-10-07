package library.application.service.retention;

import library.domain.model.reservation.Reservation;
import library.domain.model.retention.Retained;

public interface RetentionNotification {
    void retained(Retained retained);
    void notAvailable(Reservation reservation);
}
