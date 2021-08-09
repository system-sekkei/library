package library.infrastructure.datasource.loan;

import library.application.service.loan.LoanRepository;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.loan.Loan;
import library.domain.model.loan.LoanNumber;
import library.domain.model.loan.Loans;
import library.domain.model.returned.Returned;
import library.domain.model.loan.rule.LoanStatus;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.type.date.CurrentDate;
import library.infrastructure.datasource.item.ItemMapper;
import library.infrastructure.datasource.member.MemberMapper;
import library.domain.model.loan.LoanRequest;
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
    public void loan(LoanRequest loanRequest) {
        ItemNumber itemNumber = loanRequest.itemNumber();

        if (loanMapper.selectByItemNumber(itemNumber).isPresent()) {
            throw new RegisterLoanException(loanRequest);
        }

        int loanNumber = loanMapper.newLoanNumber();
        loanMapper.insertLoan(
                loanNumber,
                itemNumber,
                loanRequest.loanDate());

        itemMapper.delete貸出可能(itemNumber);
        itemMapper.insert貸出中(itemNumber);

        memberMapper.insertLoanMemberRelation(loanRequest.memberNumber(), new LoanNumber(loanNumber));
    }

    @Override
    @Transactional
    public void returned(Returned returned) {
        ItemNumber itemNumber = returned.itemNumber();
        Loan loan = loanMapper.selectByItemNumber(itemNumber)
                .orElseThrow(() -> new IllegalArgumentException(String.format("現在貸し出されていない蔵書です。蔵書番号：%s", itemNumber)));
        loanMapper.insertReturnMaterial(loan.loanNumber(), returned);

        itemMapper.insert貸出可能(itemNumber);
        itemMapper.delete貸出中(itemNumber);

        memberMapper.deleteLoanMemberRelation(loan.loanNumber());
    }


    @Override
    public LoanStatus status(MemberNumber memberNumber) {
        List<Loan> loans = memberMapper.selectLoansByMemberNumber(memberNumber);
        Member member = memberMapper.selectMember(memberNumber);

        return new LoanStatus(member, new Loans(loans), new CurrentDate(LocalDate.now()));
    }

    @Override
    public Loan findBy(ItemNumber itemNumber) {
        return loanMapper.selectByItemNumber(itemNumber).orElseThrow(() ->
                new IllegalArgumentException(String.format("現在貸し出されていない蔵書です。蔵書番号：%s", itemNumber)));
    }
}
