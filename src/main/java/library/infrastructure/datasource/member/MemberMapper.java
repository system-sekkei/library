package library.infrastructure.datasource.member;

import library.domain.model.loan.Loan;
import library.domain.model.loan.LoanNumber;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {

    boolean exists(MemberNumber memberNumber);
    Member selectMember(MemberNumber memberNumber);

    void insertLoanMemberRelation(@Param("memberNumber") MemberNumber memberNumber, @Param("loanNumber") LoanNumber loanNumber);
    List<Loan> selectLoansByMemberNumber(@Param("memberNumber") MemberNumber memberNumber);
}
