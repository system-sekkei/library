package library.infrastructure.datasource.bookonloan;

import library.application.repository.BookOnLoanRepository;
import library.domain.model.loan.loan.BookOnLoan;
import library.domain.model.loan.loan.BookOnLoans;
import library.domain.model.loan.rule.BookOnLoanRequest;
import library.domain.model.loan.rule.MemberAllBookOnLoans;
import library.domain.model.loan.loan.ReturningBookOnLoan;
import library.domain.model.book.item.Item;
import library.domain.model.book.item.ItemNumber;
import library.domain.model.book.item.HoldingOnLoan;
import library.domain.model.member.Member;
import library.infrastructure.datasource.item.HoldingMapper;
import library.infrastructure.datasource.member.MemberMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookOnLoanDataSource implements BookOnLoanRepository {
    BookOnLoanMapper bookOnLoanMapper;
    HoldingMapper holdingMapper;
    MemberMapper memberMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public BookOnLoanDataSource(BookOnLoanMapper bookOnLoanMapper, HoldingMapper holdingMapper, MemberMapper memberMapper) {
        this.bookOnLoanMapper = bookOnLoanMapper;
        this.holdingMapper = holdingMapper;
        this.memberMapper = memberMapper;
    }

    @Override
    @Transactional
    public BookOnLoan registerBookOnLoan(BookOnLoanRequest bookOnLoanRequest) {
        ItemNumber itemNumber = bookOnLoanRequest.holdingInStock().holding().itemNumber();
        holdingMapper.lockHolding(itemNumber);

        if (bookOnLoanMapper.selectByItemNumber(itemNumber).isPresent()) {
            throw new RegisterBookOnLoanException(bookOnLoanRequest);
        }

        Integer bookOnLoanId = bookOnLoanMapper.newBookOnLoanIdentifier();
        bookOnLoanMapper.insertBookOnLoan(
                bookOnLoanId,
                bookOnLoanRequest.member().memberNumber(),
                bookOnLoanRequest.holdingInStock().holding().itemNumber(),
                bookOnLoanRequest.loanDate());

        return findBookOnLoanByItemNumber(itemNumber);
    }

    @Override
    public void registerReturnBook(ReturningBookOnLoan returningBookOnLoan) {
        BookOnLoan bookOnLoan = returningBookOnLoan.bookOnLoan();
        bookOnLoanMapper.insertReturnBook(bookOnLoan.bookOnLoanId(), returningBookOnLoan.returnDate());
    }

    @Override
    public MemberAllBookOnLoans findMemberAllBookOnLoans(Member member) {
        List<BookOnLoanData> bookOnLoanDataList = bookOnLoanMapper.selectByMemberNumber(member.memberNumber());
        List<BookOnLoan> bookOnLoans = bookOnLoans(member, bookOnLoanDataList);

        return new MemberAllBookOnLoans(member, new BookOnLoans(bookOnLoans));
    }

    @Override
    public BookOnLoan findBookOnLoanByItemNumber(ItemNumber itemNumber) {
        BookOnLoanData bookOnLoanData = bookOnLoanMapper.selectByItemNumber(itemNumber).orElseThrow(() ->
                new IllegalArgumentException(String.format("現在貸し出されていない蔵書です。蔵書コード：%s", itemNumber)));

        Member member = memberMapper.selectMember(bookOnLoanData.memberNumber);
        BookOnLoan bookOnLoan = bookOnLoans(member, List.of(bookOnLoanData)).get(0);
        return bookOnLoan;
    }

    List<BookOnLoan> bookOnLoans(Member member, List<BookOnLoanData> bookOnLoanDataList) {
        if (bookOnLoanDataList.isEmpty()) return List.of();

        List<ItemNumber> itemNumbers =
                bookOnLoanDataList.stream()
                        .map(bookOnLoanData -> bookOnLoanData.itemNumber)
                        .collect(Collectors.toList());
        List<Item> items = holdingMapper.selectHoldings(itemNumbers);

        return bookOnLoanDataList.stream()
                .map(bookOnLoanData ->
                        items.stream()
                                .filter(holding -> holding.itemNumber().sameValue(bookOnLoanData.itemNumber))
                                .findFirst()
                                .map(item -> new BookOnLoan(bookOnLoanData.bookOnLoanId, member, new HoldingOnLoan(item), bookOnLoanData.loanDate))
                                .orElseThrow())
                .collect(Collectors.toList());
    }


}
