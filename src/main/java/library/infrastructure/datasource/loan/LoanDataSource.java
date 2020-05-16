package library.infrastructure.datasource.loan;

import library.application.repository.LoanRepository;
import library.domain.model.item.ItemNumber;
import library.domain.model.loan.loan.Loan;
import library.domain.model.loan.loan.Loans;
import library.domain.model.loan.returned.Returned;
import library.domain.type.date.CurrentDate;
import library.domain.model.loan.rule.LoanStatus;
import library.domain.model.loan.loan.LoanRequest;
import library.domain.model.member.Member;
import library.infrastructure.datasource.item.ItemMapper;
import library.infrastructure.datasource.member.MemberMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class LoanDataSource implements LoanRepository {
    LoanMapper loanMapper;
    ItemMapper itemMapper;
    MemberMapper memberMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public LoanDataSource(LoanMapper loanMapper, ItemMapper itemMapper, MemberMapper memberMapper) {
        this.loanMapper = loanMapper;
        this.itemMapper = itemMapper;
        this.memberMapper = memberMapper;
    }

    @Override
    @Transactional
    public void registerLoan(LoanRequest loanRequest) {
        ItemNumber itemNumber = loanRequest.item().itemNumber();

        if (loanMapper.selectByItemNumber(itemNumber).isPresent()) {
            throw new RegisterLoanException(loanRequest);
        }

        Integer bookOnLoanId = loanMapper.newLoanNumber();
        loanMapper.insertLoan(
                bookOnLoanId,
                loanRequest.member().number(),
                loanRequest.item().itemNumber(),
                loanRequest.loanDate());
    }

    @Override
    public void registerReturnBook(Returned returned) {
        ItemNumber itemNumber = returned.itemNumber();
        Loan loan = loanMapper.selectByItemNumber(itemNumber)
                .orElseThrow(() -> new IllegalArgumentException(String.format("現在貸し出されていない蔵書です。蔵書コード：%s", itemNumber)));
        loanMapper.insertReturnBook(loan.loanNumber(), returned);
    }

    @Override
    public LoanStatus loanStatus(Member member) {
        List<Loan> loans = loanMapper.selectByMemberNumber(member.number());
        return new LoanStatus(member, new Loans(loans), new CurrentDate(LocalDate.now()));
    }

    @Override
    public Loan findLoanByItemNumber(ItemNumber itemNumber) {
        return loanMapper.selectByItemNumber(itemNumber).orElseThrow(() ->
                new IllegalArgumentException(String.format("現在貸し出されていない蔵書です。蔵書コード：%s", itemNumber)));
    }
}
