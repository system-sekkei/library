package library.domain.model.loan.rule;

import library.domain.model.loan.Loan;
import library.domain.model.loan.LoanDate;
import library.domain.model.loan.Loans;
import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.material.entry.EntryType;
import library.domain.model.material.item.Item;
import library.domain.model.material.item.ItemNumber;
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

class LoanStatusTest {
    @ParameterizedTest
    @CsvSource({
            "中学生以上, 2020-02-19, , 19, 0, 貸出可能",
            "中学生以上, 2020-02-19, , 20, 0, 冊数制限により貸出不可",
            "中学生以上, 2020-02-19, 2020-02-19, 0, 5, 視聴覚資料貸出不可",
            "中学生以上, 2020-02-19, 2020-02-18, 1, 1, 新規貸出不可",
            "中学生以上, 2020-02-19, 2020-01-05, 1, 1, 新規貸出不可",
            "中学生以上, 2020-02-19, 2020-01-04, 1, 1, 貸出一定期間停止",
            "小学生以下, 2020-02-19, , 14, 0, 貸出可能",
            "小学生以下, 2020-02-19, , 15, 0, 冊数制限により貸出不可",
            "小学生以下, 2020-02-19, 2020-02-19, 0, 5, 視聴覚資料貸出不可",
            "小学生以下, 2020-02-19, 2020-02-18, 1, 1, 新規貸出不可",
            "小学生以下, 2020-02-19, 2020-01-05, 1, 1, 新規貸出不可",
            "小学生以下, 2020-02-19, 2020-01-04, 1, 1, 貸出一定期間停止"
    })
    void 貸出制限の判定ができる(MemberType memberType, String loanDate1, String loanDate2, int 借りている図書の冊数, int 借りている視聴覚資料の数, Loanability expected) {
        CurrentDate currentDate = CurrentDate.parse("2020-03-18");
        MemberNumber memberNumber = new MemberNumber(1);
        Member member = new Member(memberNumber, new Name(""), memberType);

        List<Item> 借りている図書 = new ArrayList<>();
        for (int i = 0; i < 借りている図書の冊数; i++) {
            借りている図書.add(new Item(null, new Entry(null, null, null, EntryType.図書)));
        }

        List<Item> 借りている視聴覚資料 = new ArrayList<>();
        for (int i = 0; i < 借りている視聴覚資料の数; i++) {
            借りている視聴覚資料.add(new Item(null, new Entry(null, null, null, EntryType.視聴覚資料)));
        }

        List<Loan> loans = new ArrayList<>();
        借りている図書.forEach(item -> loans.add((new Loan(null, member, item, LoanDate.parse(loanDate1)))));
        借りている視聴覚資料.forEach(item -> loans.add((new Loan(null, member, item, LoanDate.parse(loanDate2)))));

        LoanStatus loanStatus = new LoanStatus(member, new Loans(loans), currentDate);

        Item 借りたい所蔵品 = new Item(new ItemNumber("11-A"), new Entry(new EntryNumber(11), null, null, EntryType.視聴覚資料));

        assertEquals(expected, loanStatus.貸出可否判定(借りたい所蔵品));

    }
}


