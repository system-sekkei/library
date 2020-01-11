package library.domain.model.reserving;

import library.domain.model.book.Book;
import library.domain.model.member.Member;

/**
 * 貸出予約
 */
public class Reserving {
    Member member;
    Book book;
    LoanPreparationCompletionDate loanPreparationCompletionDate;
}
