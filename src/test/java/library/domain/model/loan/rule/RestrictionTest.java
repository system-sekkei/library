package library.domain.model.loan.rule;

import library.domain.model.delay.DelayStatus;
import library.domain.model.loan.Loan;
import library.domain.model.loan.LoanDate;
import library.domain.model.loan.LoanNumber;
import library.domain.model.loan.Loans;
import library.domain.model.material.entry.*;
import library.domain.model.material.item.Item;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.member.MemberType;
import library.domain.model.member.Name;
import library.domain.type.date.CurrentDate;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestrictionTest {
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


    @Nested
    class 遅延期間での貸出判定 {

        @Test
        void 遅延日数１５日未満での貸出可否判定を取得する() {
            // Arrange
            // TODO 貸出リスト（Loans）と貸出要求から貸出制限が導出されるのが自然？遅延状態はメソッド内で導出
            Restriction sut = new Restriction(小学生, 貸出リスト, あなたの絵に物語性を与える方法, DelayStatus.遅延日数１５日未満);
            // Act
            Loanability 貸出可否判定 = sut.貸出可否判定();
            // Assert
            assertEquals(Loanability.貸出可能, 貸出可否判定);

        }

        @Test
        void 遅延日数２ヶ月未満で貸出可否判定を取得する() {
            // Arrange
            Restriction sut = new Restriction(小学生, 貸出リスト, あなたの絵に物語性を与える方法, DelayStatus.遅延日数２ヶ月未満);
            // Act
            Loanability 貸出可否判定 = sut.貸出可否判定();
            // Assert
            assertEquals(Loanability.新規貸出不可, 貸出可否判定);

        }

        @Test
        void 遅延日数２ヶ月以上で貸出可否判定を取得する() {
            // Arrange
            Restriction sut = new Restriction(小学生, 貸出リスト, あなたの絵に物語性を与える方法, DelayStatus.遅延日数２ヶ月以上);
            // Act
            Loanability 貸出可否判定 = sut.貸出可否判定();
            // Assert
            assertEquals(Loanability.貸出一定期間停止, 貸出可否判定);

        }

        Loans 貸出リスト = new Loans(List.of(
                new Loan(new LoanNumber(1), 小学生, リファクタリング, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 小学生, 耳をすませば, new LoanDate(LocalDate.parse("2023-06-20"))),
                new Loan(new LoanNumber(1), 小学生, オペラ座の怪人, new LoanDate(LocalDate.parse("2023-07-01")))
        ));

        Item あなたの絵に物語性を与える方法 = new Item(
                new ItemNumber("89891"),
                new Entry(new EntryNumber("7892"),
                        new Title("あなたの絵に物語性を与える方法"),
                        new WorkOf("加藤 オズワルド"),
                        EntryType.図書));
    }

    @Nested
    class 会員種別での貸出判定 {

        @Test
        void 小学生の貸出上限以上での貸出判定() {
            // Arrange
            Restriction sut = new Restriction(小学生, 小学生の上限の貸出リスト, あなたの絵に物語性を与える方法, DelayStatus.遅延日数１５日未満);
            // Act
            Loanability 貸出可否判定 = sut.貸出可否判定();
            // Assert
            assertEquals(Loanability.冊数制限により貸出不可, 貸出可否判定);
        }

        @Test
        void 小学生の貸出上限以内での貸出判定() {
            // Arrange
            Restriction sut = new Restriction(小学生, 小学生の貸出リスト, あなたの絵に物語性を与える方法, DelayStatus.遅延日数１５日未満);
            // Act
            Loanability 貸出可否判定 = sut.貸出可否判定();
            // Assert
            assertEquals(Loanability.貸出可能, 貸出可否判定);
        }


        @Test
        void 一般の貸出上限以上での貸出判定() {
            // Arrange
            Restriction sut = new Restriction(小学生, 一般の上限の貸出リスト, あなたの絵に物語性を与える方法, DelayStatus.遅延日数１５日未満);
            // Act
            Loanability 貸出可否判定 = sut.貸出可否判定();
            // Assert
            assertEquals(Loanability.冊数制限により貸出不可, 貸出可否判定);
        }

        @Test
        void 一般の貸出上限以内での貸出判定() {
            // Arrange
            Restriction sut = new Restriction(小学生, 一般の貸出リスト, あなたの絵に物語性を与える方法, DelayStatus.遅延日数１５日未満);
            // Act
            Loanability 貸出可否判定 = sut.貸出可否判定();
            // Assert
            assertEquals(Loanability.貸出可能, 貸出可否判定);
        }


        Loans 小学生の上限の貸出リスト = new Loans(List.of(
                new Loan(new LoanNumber(1), 小学生, リファクタリング, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 小学生, プロダクトマネジメントのすべて, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 小学生, 技術書の読書術, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 小学生, 思考法図鑑, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 小学生, 技術書の読書術, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 小学生, はじめてのテストプロセス改善, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 小学生, 超ど素人がはじめる投資信託, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 小学生, 独習Git, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 小学生, 予想どおりに不合理, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 小学生, AI人材の育て方, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 小学生, ゴッドファーザー, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 小学生, ゴッドファーザー2, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 小学生, ゴッドファーザー3, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 小学生, 耳をすませば, new LoanDate(LocalDate.parse("2023-06-20"))),
                new Loan(new LoanNumber(1), 小学生, オペラ座の怪人, new LoanDate(LocalDate.parse("2023-07-01")))
        ));

        Loans 一般の上限の貸出リスト = new Loans(List.of(
                new Loan(new LoanNumber(1), 一般, リファクタリング, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 一般, プロダクトマネジメントのすべて, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 一般, 技術書の読書術, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 一般, 思考法図鑑, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 一般, 技術書の読書術, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 一般, はじめてのテストプロセス改善, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 一般, 超ど素人がはじめる投資信託, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 一般, 独習Git, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 一般, 予想どおりに不合理, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 一般, プロになるためのSpring入門, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 一般, Kubernetesの知識地図, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 一般, これまでの仕事これからの仕事, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 一般, 因果推論入門, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 一般, Webアプリケーションアクセシビリティ, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 一般, AI人材の育て方, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 一般, ゴッドファーザー, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 一般, ゴッドファーザー2, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 一般, ゴッドファーザー3, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 一般, 耳をすませば, new LoanDate(LocalDate.parse("2023-06-20"))),
                new Loan(new LoanNumber(1), 一般, オペラ座の怪人, new LoanDate(LocalDate.parse("2023-07-01")))
        ));

        Loans 小学生の貸出リスト = new Loans(List.of(
                new Loan(new LoanNumber(1), 小学生, リファクタリング, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 小学生, 耳をすませば, new LoanDate(LocalDate.parse("2023-06-20"))),
                new Loan(new LoanNumber(1), 小学生, オペラ座の怪人, new LoanDate(LocalDate.parse("2023-07-01")))
        ));

        Loans 一般の貸出リスト = new Loans(List.of(
                new Loan(new LoanNumber(1), 一般, プロになるためのSpring入門, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 一般, Kubernetesの知識地図, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 一般, これまでの仕事これからの仕事, new LoanDate(LocalDate.parse("2023-06-21"))),
                new Loan(new LoanNumber(1), 一般, オペラ座の怪人, new LoanDate(LocalDate.parse("2023-07-01")))
        ));

    }


    Member 小学生 = new Member(new MemberNumber(90009), new Name("山本一郎"), MemberType.小学生以下);
    Member 一般 = new Member(new MemberNumber(90019), new Name("加治隆介"), MemberType.中学生以上);

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


    Item プロダクトマネジメントのすべて = new Item(
            new ItemNumber("044-072121-5"),
            new Entry(new EntryNumber("7892"),
                    new Title("プロダクトマネジメントのすべて"),
                    new WorkOf("及川 卓也"),
                    EntryType.図書));

    Item 技術書の読書術 = new Item(
            new ItemNumber("044-072121-5"),
            new Entry(new EntryNumber("7892"),
                    new Title("技術書の読書術"),
                    new WorkOf("増井 敏克"),
                    EntryType.図書));

    Item 思考法図鑑 = new Item(
            new ItemNumber("044-072121-5"),
            new Entry(new EntryNumber("7892"),
                    new Title("思考法図鑑"),
                    new WorkOf("株式会社アンド"),
                    EntryType.図書));

    Item あなたの絵に物語性を与える方法 = new Item(
            new ItemNumber("044-072121-5"),
            new Entry(new EntryNumber("7892"),
                    new Title("あなたの絵に物語性を与える方法"),
                    new WorkOf("加藤 オズワルド"),
                    EntryType.図書));

    Item はじめてのテストプロセス改善 = new Item(
            new ItemNumber("044-072121-5"),
            new Entry(new EntryNumber("7892"),
                    new Title("はじめてのテストプロセス改善"),
                    new WorkOf("高木 陽平"),
                    EntryType.図書));

    Item 超ど素人がはじめる投資信託 = new Item(
            new ItemNumber("044-072121-5"),
            new Entry(new EntryNumber("7892"),
                    new Title("超ど素人がはじめる投資信託"),
                    new WorkOf("上本 敏雅"),
                    EntryType.図書));

    Item 独習Git = new Item(
            new ItemNumber("044-072121-5"),
            new Entry(new EntryNumber("7892"),
                    new Title("独習Git"),
                    new WorkOf("吉川邦夫"),
                    EntryType.図書));

    Item 予想どおりに不合理 = new Item(
            new ItemNumber("044-072121-5"),
            new Entry(new EntryNumber("7892"),
                    new Title("予想どおりに不合理"),
                    new WorkOf("ダン アリエリー"),
                    EntryType.図書));

    Item AI人材の育て方 = new Item(
            new ItemNumber("044-072121-5"),
            new Entry(new EntryNumber("7892"),
                    new Title("AI人材の育て方"),
                    new WorkOf("孝忠 大輔"),
                    EntryType.図書));


    Item ゴッドファーザー = new Item(
            new ItemNumber("044-072121-5"),
            new Entry(new EntryNumber("1972"),
                    new Title("ゴッドファーザー"),
                    new WorkOf("Francis Ford Coppola"),
                    EntryType.視聴覚資料));

    Item ゴッドファーザー2 = new Item(
            new ItemNumber("044-072121-5"),
            new Entry(new EntryNumber("1974"),
                    new Title("ゴッドファーザー2"),
                    new WorkOf("Francis Ford Coppola"),
                    EntryType.視聴覚資料));

    Item ゴッドファーザー3 = new Item(
            new ItemNumber("044-072121-5"),
            new Entry(new EntryNumber("1974"),
                    new Title("ゴッドファーザー3"),
                    new WorkOf("Francis Ford Coppola"),
                    EntryType.視聴覚資料));

    Item プロになるためのSpring入門 = new Item(
            new ItemNumber("044-072121-5"),
            new Entry(new EntryNumber("7892"),
                    new Title("プロになるためのSpring入門"),
                    new WorkOf("土岐孝平"),
                    EntryType.図書));

    Item Kubernetesの知識地図 = new Item(
            new ItemNumber("044-072121-5"),
            new Entry(new EntryNumber("7892"),
                    new Title("Kubernetesの知識地図"),
                    new WorkOf("青山真也"),
                    EntryType.図書));

    Item これまでの仕事これからの仕事 = new Item(
            new ItemNumber("044-072121-5"),
            new Entry(new EntryNumber("7892"),
                    new Title("これまでの仕事これからの仕事"),
                    new WorkOf("市谷聡啓"),
                    EntryType.図書));

    Item 因果推論入門 = new Item(
            new ItemNumber("044-072121-5"),
            new Entry(new EntryNumber("7892"),
                    new Title("因果推論入門"),
                    new WorkOf("Scott Cunningham"),
                    EntryType.図書));

    Item Webアプリケーションアクセシビリティ = new Item(
            new ItemNumber("044-072121-5"),
            new Entry(new EntryNumber("7892"),
                    new Title("独習Git"),
                    new WorkOf("伊原力也"),
                    EntryType.図書));
}