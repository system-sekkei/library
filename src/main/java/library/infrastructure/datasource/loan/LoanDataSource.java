package library.infrastructure.datasource.loan;

import library.application.service.loan.LoanRepository;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.loan.Loan;
import library.domain.model.loan.LoanNumber;
import library.domain.model.loan.Loans;
import library.domain.model.retention.Retained;
import library.domain.model.returned.Returned;
import library.domain.model.loan.rule.LoanStatus;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.type.date.CurrentDate;
import library.infrastructure.datasource.item.ItemMapper;
import library.infrastructure.datasource.member.MemberMapper;
import library.domain.model.loan.LoanRequest;
import library.infrastructure.datasource.reservation.ReservationMapper;
import library.infrastructure.datasource.retention.RetentionMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class LoanDataSource implements LoanRepository {
    LoanMapper loanMapper;
    ItemMapper itemMapper;
    MemberMapper memberMapper;
    ReservationMapper reservationMapper;
    RetentionMapper retentionMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public LoanDataSource(LoanMapper loanMapper, ItemMapper itemMapper, MemberMapper memberMapper, ReservationMapper reservationMapper, RetentionMapper retentionMapper) {
        this.loanMapper = loanMapper;
        this.itemMapper = itemMapper;
        this.memberMapper = memberMapper;
        this.reservationMapper = reservationMapper;
        this.retentionMapper = retentionMapper;
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
                .orElseThrow(() -> new IllegalArgumentException(String.format("現在貸し出されていない所蔵品です。所蔵品番号：%s", itemNumber)));
        loanMapper.insertReturnMaterial(loan.loanNumber(), returned);

        itemMapper.insert貸出可能(itemNumber);
        itemMapper.delete貸出中(itemNumber);

        memberMapper.deleteLoanMemberRelation(loan.loanNumber());
    }

    @Override
    @Transactional
    public void loan(LoanRequest loanRequest, Retained retained) {
        ItemNumber itemNumber = retained.itemNumber();

        if (loanMapper.selectByItemNumber(itemNumber).isPresent()) {
            throw new RegisterLoanException(loanRequest);
        }

        int loanNumber = loanMapper.newLoanNumber();
        loanMapper.insertLoan(
                loanNumber,
                itemNumber,
                loanRequest.loanDate());

        itemMapper.delete取置中(itemNumber);
        itemMapper.insert貸出中(itemNumber);

        memberMapper.insertLoanMemberRelation(loanRequest.memberNumber(), new LoanNumber(loanNumber));

        // 予約履歴削除
        memberMapper.delete予約と会員(retained.reservationNumber());

        // 取置の状態変更
        retentionMapper.insert取置解放履歴(retained.reservationNumber(), itemNumber);
        retentionMapper.delete準備完了(itemNumber);
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
                new IllegalArgumentException(String.format("現在貸し出されていない所蔵品です。所蔵品番号：%s", itemNumber)));
    }
}
