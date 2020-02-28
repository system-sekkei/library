package library.application.repository;

import library.domain.model.book.Book;
import library.domain.model.member.Member;

public interface ReservationRepository {
    void registerReservation(Member member, Book book);
}
