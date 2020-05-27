package library.application.repository;

import library.domain.model.reservation.request.Reservation;
import library.domain.model.reservation.retention.Retained;

public interface RetentionNotification {
    void retained(Retained retained);
    void notAvailable(Reservation reservation);
}
