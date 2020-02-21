package library.application.service.bookonloan;

import library.application.repository.BookOnLoanRepository;
import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookonloan.loan.BookOnLoan;
import library.domain.model.bookonloan.loan.MemberAllBookOnLoans;
import library.domain.model.member.Member;
import org.springframework.stereotype.Service;

/**
 * 貸出図書参照サービス
 */
@Service
public class BookOnLoanQueryService {
    BookOnLoanRepository bookOnLoanRepository;

    BookOnLoanQueryService(BookOnLoanRepository bookOnLoanRepository) {
        this.bookOnLoanRepository = bookOnLoanRepository;
    }

    public MemberAllBookOnLoans findMemberAllBookOnLoans(Member member) {
        return bookOnLoanRepository.findMemberAllBookOnLoans(member);
    }

    /**
     * 蔵書コードによる貸出図書取得
     */
    public BookOnLoan findBookOnLoanByBookCollectionCode(BookCollectionCode bookCollectionCode) {
        return bookOnLoanRepository.findBookOnLoanByBookCollectionCode(bookCollectionCode);
    }
}