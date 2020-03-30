package library.application.service.bookonloan;

import library.application.repository.BookOnLoanRepository;
import library.domain.model.bookonloan.loan.BookOnLoan;
import library.domain.model.bookonloan.loaning.MemberAllBookOnLoans;
import library.domain.model.holding.HoldingCode;
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

    /**
     * 会員の全貸出図書の一覧を取得する
     */
    public MemberAllBookOnLoans findMemberAllBookOnLoans(Member member) {
        return bookOnLoanRepository.findMemberAllBookOnLoans(member);
    }

    /**
     * 蔵書コードによる貸出図書を取得する
     */
    public BookOnLoan findBookOnLoanByHoldingCode(HoldingCode holdingCode) {
        return bookOnLoanRepository.findBookOnLoanByHoldingCode(holdingCode);
    }
}