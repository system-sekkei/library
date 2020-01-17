package library.domain.model.reservation;

import library.domain.model.book.Book;
import library.domain.model.member.Member;

/**
 * 貸出予約
 */
public class Reservation {
    Member member;
    Book book;
    RetentionStatus retentionStatus;

}
