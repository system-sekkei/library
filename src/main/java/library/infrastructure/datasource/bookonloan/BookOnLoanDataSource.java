package library.infrastructure.datasource.bookonloan;

import library.application.repository.BookOnLoanRepository;
import library.domain.model.holding.Holding;
import library.domain.model.holding.HoldingCode;
import library.domain.model.holding.HoldingOnLoan;
import library.domain.model.bookonloan.loan.BookOnLoan;
import library.domain.model.bookonloan.loan.BookOnLoans;
import library.domain.model.bookonloan.loaning.BookOnLoanRequest;
import library.domain.model.bookonloan.loaning.MemberAllBookOnLoans;
import library.domain.model.bookonloan.returning.ReturningBookOnLoan;
import library.domain.model.member.Member;
import library.infrastructure.datasource.holding.HoldingMapper;
import library.infrastructure.datasource.member.MemberMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookOnLoanDataSource implements BookOnLoanRepository {
    BookOnLoanMapper mapper;
    HoldingMapper holdingMapper;
    MemberMapper memberMapper;

    public BookOnLoanDataSource(BookOnLoanMapper mapper, HoldingMapper holdingMapper, MemberMapper memberMapper) {
        this.mapper = mapper;
        this.holdingMapper = holdingMapper;
        this.memberMapper = memberMapper;
    }

    @Override
    @Transactional
    public BookOnLoan registerBookOnLoan(BookOnLoanRequest bookOnLoanRequest) {
        HoldingCode holdingCode = bookOnLoanRequest.holdingInStock().holding().holdingCode();
        holdingMapper.lockHolding(holdingCode);

        Holding holding = holdingMapper.selectHolding(holdingCode);

        if (holding.holdingStatus().outOnLoan()) {
            throw new RegisterBookOnLoanException(bookOnLoanRequest);
        }

        Integer bookOnLoanId = mapper.newBookOnLoanIdentifier();
        mapper.insertBookOnLoan(
                bookOnLoanId,
                bookOnLoanRequest.member().memberNumber(),
                bookOnLoanRequest.holdingInStock().holding().holdingCode(),
                bookOnLoanRequest.loanDate());

        return findBookOnLoanByHoldingCode(holdingCode);
    }

    @Override
    public void registerReturnBook(ReturningBookOnLoan returningBookOnLoan) {
        BookOnLoan bookOnLoan = returningBookOnLoan.bookOnLoan();
        mapper.insertReturnBook(bookOnLoan.bookOnLoanId(), returningBookOnLoan.returnDate());
    }

    @Override
    public MemberAllBookOnLoans findMemberAllBookOnLoans(Member member) {
        List<BookOnLoanData> bookOnLoanDataList = mapper.selectByMemberNumber(member.memberNumber());
        List<BookOnLoan> bookOnLoans = bookOnLoans(member, bookOnLoanDataList);

        return new MemberAllBookOnLoans(member, new BookOnLoans(bookOnLoans));
    }

    @Override
    public BookOnLoan findBookOnLoanByHoldingCode(HoldingCode holdingCode) {
        BookOnLoanData bookOnLoanData = mapper.selectByHoldingCode(holdingCode).orElseThrow(() ->
            new IllegalArgumentException(String.format("現在貸し出されていない蔵書です。蔵書コード：%s", holdingCode)));

        Member member = memberMapper.selectMember(bookOnLoanData.memberNumber);
        BookOnLoan bookOnLoan = bookOnLoans(member, List.of(bookOnLoanData)).get(0);
        return bookOnLoan;
    }

    List<BookOnLoan> bookOnLoans(Member member, List<BookOnLoanData> bookOnLoanDataList) {
        if (bookOnLoanDataList.isEmpty()) return List.of();

        List<HoldingCode> holdingCodes =
                bookOnLoanDataList.stream()
                        .map(bookOnLoanData -> bookOnLoanData.holdingCode)
                        .collect(Collectors.toList());
        List<Holding> holdings = holdingMapper.selectHoldings(holdingCodes);

        return bookOnLoanDataList.stream()
                .map(bookOnLoanData ->
                        holdings.stream()
                                .filter(holding -> holding.holdingCode().sameValue(bookOnLoanData.holdingCode))
                                .findFirst()
                                .map(holding -> new BookOnLoan(bookOnLoanData.bookOnLoanId, member, new HoldingOnLoan(holding), bookOnLoanData.loanDate))
                                .orElseThrow())
                .collect(Collectors.toList());
    }


}
