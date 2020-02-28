package library.infrastructure.datasource.reservation;

import library.application.repository.ReservationRepository;
import library.domain.model.book.Book;
import library.domain.model.member.Member;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDatasource implements ReservationRepository {

    @Override
    public void registerReservation(Member member, Book book) {
        // TODO:
    }
}
