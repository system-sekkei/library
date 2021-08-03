package library.domain.model.loan.rule;

import library.domain.model.loan.Loan;
import library.domain.model.loan.LoanDate;
import library.domain.model.loan.Loans;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.member.MemberType;
import library.domain.model.member.Name;
import library.domain.type.date.CurrentDate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestrictionTest {

    @ParameterizedTest
    @CsvSource({
            "中学生以上, 2020-02-19, , 貸出２０点まで",
            "中学生以上, 2020-02-19, 2020-02-18, 新規貸出不可",
            "中学生以上, 2020-02-19, 2020-01-05, 新規貸出不可",
            "中学生以上, 2020-02-19, 2020-01-04, 貸出予約停止",
            "小学生以下, 2020-02-19, , 貸出１５点まで",
            "小学生以下, 2020-02-19, 2020-02-18, 新規貸出不可",
            "小学生以下, 2020-02-19, 2020-01-05, 新規貸出不可",
            "小学生以下, 2020-02-19, 2020-01-04, 貸出予約停止"
    })
    void 貸出制限の判定ができる(MemberType memberType, String loanDate1, String loanDate2, RestrictionOfQuantity expected) {
        CurrentDate currentDate = CurrentDate.parse("2020-03-18");
        MemberNumber memberNumber = new MemberNumber(1);
        Member member = new Member(memberNumber, new Name(""), memberType);
        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan(null, member, null, LoanDate.parse(loanDate1)));

        if (loanDate2 != null) {
            loans.add(new Loan(null, member, null, LoanDate.parse(loanDate2)));
        }

        Restriction restriction = new Restriction(member, new Loans(loans), currentDate);

        assertEquals(expected, restriction.ofQuantity());
    }
}