package library.domain.model.loan;

import library.domain.model.material.entry.*;
import library.domain.model.material.item.Item;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.member.MemberType;
import library.domain.model.member.Name;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 貸出リストのテスト
 */
class LoansTest {

    @Test
    void 視聴覚資料の数() {
        Member member = new Member(new MemberNumber(100), new Name("池田瑛紗"), MemberType.中学生以上);
        Item リファクタリング = new Item(new ItemNumber("044-072121-5"),
                new Entry(new EntryNumber("7892"),
                        new Title("リファクタリング(第2版): 既存のコードを安全に改善する (OBJECT TECHNOLOGY SERIES)"),
                        new WorkOf("マーチン・ファウラー"),
                        EntryType.図書));
        Item 耳をすませば = new Item(new ItemNumber("068-089870-1"),
                new Entry(new EntryNumber("1993"),
                        new Title("耳をすませば"),
                        new WorkOf("スタジオ・ジブリ"),
                        EntryType.視聴覚資料));
        Item オペラ座の怪人 = new Item(new ItemNumber("072-123121-2"),
                new Entry(new EntryNumber("2003"),
                        new Title("オペラ座の怪人"),
                        new WorkOf("Andrew Lloyd Webber"),
                        EntryType.視聴覚資料));
        List<Loan> list = new ArrayList<>();
        list.add(new Loan(new LoanNumber(1), member, リファクタリング, new LoanDate(LocalDate.parse("2023-06-21"))));
        list.add(new Loan(new LoanNumber(1), member, 耳をすませば, new LoanDate(LocalDate.parse("2023-06-20"))));
        list.add(new Loan(new LoanNumber(1), member, オペラ座の怪人, new LoanDate(LocalDate.parse("2023-07-01"))));

        Loans sut = new Loans(list);

        NumberOfLoans 視聴覚資料の数 = sut.視聴覚資料の数();
        assertEquals(2, 視聴覚資料の数.value());
    }
}