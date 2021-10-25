package library.infrastructure.datasource.member;

import library.domain.model.loan.Loan;
import library.domain.model.loan.LoanNumber;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.ReservationNumber;
import library.domain.model.retention.RetentionNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {

    boolean exists(MemberNumber memberNumber);
    Member selectMember(MemberNumber memberNumber);

    void insertLoanMemberRelation(@Param("memberNumber") MemberNumber memberNumber, @Param("loanNumber") LoanNumber loanNumber);
    List<Loan> selectLoansByMemberNumber(@Param("memberNumber") MemberNumber memberNumber);
    void deleteLoanMemberRelation(@Param("loanNumber") LoanNumber loanNumber);
    void insert予約と会員(@Param("memberNumber") MemberNumber memberNumber, @Param("reservationNumber") ReservationNumber reservationNumber);
    void delete予約と会員(@Param("reservationNumber") ReservationNumber reservationNumber);
    void insert取置と会員(@Param("memberNumber") MemberNumber memberNumber, @Param("retentionNumber") RetentionNumber retentionNumber);
    void delete取置と会員(@Param("retentionNumber") RetentionNumber retentionNumber);
}
