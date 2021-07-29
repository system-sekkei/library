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
            "中学生以上, 2020-01-04, , 貸出５冊まで",
            "中学生以上, 2020-01-04, 2020-01-05, 貸出不可",
            "中学生以上, 2020-01-04, 2020-01-02, 貸出不可",
            "中学生以上, 2020-01-04, 2020-01-01, 貸出不可",
            "小学生以下, 2020-01-04, , 貸出７冊まで",
            "小学生以下, 2020-01-04, 2020-01-05, 貸出４冊まで",
            "小学生以下, 2020-01-04, 2020-01-02, 貸出４冊まで",
            "小学生以下, 2020-01-04, 2020-01-01, 貸出不可"
    })
    void 貸出制限の判定ができる(MemberType memberType, String loanDate1, String loanDate2, RestrictionOfQuantity expected) {
        CurrentDate currentDate = CurrentDate.parse("2020-01-20");
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