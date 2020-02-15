package library.application.repository;

import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookonloan.BookOnLoan;
import library.domain.model.bookonloan.MemberAllBookOnLoans;
import library.domain.model.bookonloan.ReturnDate;
import library.domain.model.member.Member;

/**
 * 貸出図書リポジトリ
 */
public interface BookOnLoanRepository {

    void registerBookOnLoan(BookOnLoan bookOnLoan);

    void registerReturnBook(BookOnLoan bookOnLoan, ReturnDate returnDate);

    MemberAllBookOnLoans findMemberAllBookOnLoans(Member member);

    BookOnLoan findBookOnLoanByBookCollectionCode(BookCollectionCode bookCollectionCode);
}
