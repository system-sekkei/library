package library.domain.model.reservation.rule;

import library.domain.model.material.entry.*;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.member.MemberType;
import library.domain.model.member.Name;
import library.domain.model.reservation.Reservation;
import library.domain.model.reservation.ReservationNumber;
import library.domain.model.reservation.Reservations;
import library.domain.model.reservation.availability.ReservationAvailability;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 予約制限のテスト
 */
class ReservationRestrictionTest {

    @Nested
    class 視聴覚資料 {
        Reservations 視聴覚資料の上限の予約リスト = new Reservations(List.of(
                new Reservation(new ReservationNumber("10001"), member, リファクタリング),
                new Reservation(new ReservationNumber("10002"), member, 耳をすませば),
                new Reservation(new ReservationNumber("10003"), member, オペラ座の怪人),
                new Reservation(new ReservationNumber("10004"), member, ゴッドファーザー),
                new Reservation(new ReservationNumber("10005"), member, ゴッドファーザー2),
                new Reservation(new ReservationNumber("10006"), member, ゴッドファーザー3)
        ));

        Reservations 視聴覚資料の上限未満の予約リスト = new Reservations(List.of(
                new Reservation(new ReservationNumber("10001"), member, リファクタリング),
                new Reservation(new ReservationNumber("10003"), member, オペラ座の怪人),
                new Reservation(new ReservationNumber("10004"), member, ゴッドファーザー),
                new Reservation(new ReservationNumber("10005"), member, ゴッドファーザー2),
                new Reservation(new ReservationNumber("10006"), member, ゴッドファーザー3)
        ));


        @Test
        void 予約上限に達している場合_予約不可を返却する() {
            Entry 予約対象資料 = new Entry(new EntryNumber(123), new Title("Star wars"), new WorkOf("George Lucas"), EntryType.視聴覚資料);
            ReservationRestriction sut = new ReservationRestriction(null,  視聴覚資料の上限の予約リスト); //TODO memberを使っていないので、予約制限クラスに会員は不要では？

            ReservationAvailability 予約可否判定結果 = sut.予約可否判定(予約対象資料);

            assertEquals(ReservationAvailability.視聴覚資料予約不可, 予約可否判定結果);
        }

        @Test
        void 予約上限に未満の場合_予約可を返却する() {
            Entry 予約対象資料 = new Entry(new EntryNumber(123), new Title("Star wars"), new WorkOf("George Lucas"), EntryType.視聴覚資料);
            ReservationRestriction sut = new ReservationRestriction(null,  視聴覚資料の上限未満の予約リスト); //TODO memberを使っていないので、予約制限クラスに会員は不要では？

            ReservationAvailability 予約可否判定結果 = sut.予約可否判定(予約対象資料);

            assertEquals(ReservationAvailability.予約可能, 予約可否判定結果);
        }

    }

    @Nested
    class 図書資料 {
        Reservations 予約上限の予約リスト = new Reservations(List.of(
                new Reservation(new ReservationNumber("10001"), member, リファクタリング),
                new Reservation(new ReservationNumber("10003"), member, プロダクトマネジメントのすべて),
                new Reservation(new ReservationNumber("10004"), member, 技術書の読書術),
                new Reservation(new ReservationNumber("10005"), member, 思考法図鑑),
                new Reservation(new ReservationNumber("10006"), member, あなたの絵に物語性を与える方法),
                new Reservation(new ReservationNumber("10010"), member, はじめてのテストプロセス改善),
                new Reservation(new ReservationNumber("10011"), member, 超ど素人がはじめる投資信託),
                new Reservation(new ReservationNumber("10012"), member, 独習Git),
                new Reservation(new ReservationNumber("10014"), member, 予想どおりに不合理),
                new Reservation(new ReservationNumber("10015"), member, AI人材の育て方),
                new Reservation(new ReservationNumber("90002"), member, 耳をすませば),
                new Reservation(new ReservationNumber("90003"), member, オペラ座の怪人),
                new Reservation(new ReservationNumber("90004"), member, ゴッドファーザー),
                new Reservation(new ReservationNumber("90005"), member, ゴッドファーザー2),
                new Reservation(new ReservationNumber("90006"), member, ゴッドファーザー3)
        ));

        Reservations 予約上限未満の予約リスト = new Reservations(List.of(
                new Reservation(new ReservationNumber("10003"), member, プロダクトマネジメントのすべて),
                new Reservation(new ReservationNumber("10004"), member, 技術書の読書術),
                new Reservation(new ReservationNumber("10005"), member, 思考法図鑑),
                new Reservation(new ReservationNumber("10006"), member, あなたの絵に物語性を与える方法),
                new Reservation(new ReservationNumber("10010"), member, はじめてのテストプロセス改善),
                new Reservation(new ReservationNumber("10011"), member, 超ど素人がはじめる投資信託),
                new Reservation(new ReservationNumber("10012"), member, 独習Git),
                new Reservation(new ReservationNumber("10014"), member, 予想どおりに不合理),
                new Reservation(new ReservationNumber("10015"), member, AI人材の育て方),
                new Reservation(new ReservationNumber("90002"), member, 耳をすませば),
                new Reservation(new ReservationNumber("90003"), member, オペラ座の怪人),
                new Reservation(new ReservationNumber("90004"), member, ゴッドファーザー),
                new Reservation(new ReservationNumber("90005"), member, ゴッドファーザー2),
                new Reservation(new ReservationNumber("90006"), member, ゴッドファーザー3)
        ));

        @Test
        void 予約上限に達している場合_予約不可を返却する() {
            Entry 予約対象資料 = new Entry(new EntryNumber(123), new Title("図解まるわかり AWSのしくみ"), new WorkOf("西村 泰洋"), EntryType.図書);
            ReservationRestriction sut = new ReservationRestriction(null, 予約上限の予約リスト); //TODO memberを使っていないので、予約制限クラスに会員は不要では？

            ReservationAvailability 予約可否判定結果 = sut.予約可否判定(予約対象資料);

            assertEquals(ReservationAvailability.冊数制限により予約不可, 予約可否判定結果);
        }

        @Test
        void 予約上限に未満の場合_予約可を返却する() {
            Entry 予約対象資料 = new Entry(new EntryNumber(123), new Title("図解まるわかり AWSのしくみ"), new WorkOf("西村 泰洋"), EntryType.図書);
            ReservationRestriction sut = new ReservationRestriction(null, 予約上限未満の予約リスト); //TODO memberを使っていないので、予約制限クラスに会員は不要では？

            ReservationAvailability 予約可否判定結果 = sut.予約可否判定(予約対象資料);

            assertEquals(ReservationAvailability.予約可能, 予約可否判定結果);
        }
    }

    Member member = new Member(new MemberNumber(100), new Name("池田瑛紗"), MemberType.中学生以上);
    Entry リファクタリング = new Entry(new EntryNumber("7892"),
            new Title("リファクタリング(第2版): 既存のコードを安全に改善する (OBJECT TECHNOLOGY SERIES)"),
            new WorkOf("マーチン・ファウラー"),
            EntryType.図書);

    Entry プロダクトマネジメントのすべて = new Entry(new EntryNumber("7892"),
            new Title("プロダクトマネジメントのすべて"),
            new WorkOf("及川 卓也"),
            EntryType.図書);

    Entry 技術書の読書術  = new Entry(new EntryNumber("7892"),
            new Title("技術書の読書術"),
            new WorkOf("増井 敏克"),
            EntryType.図書);

    Entry 思考法図鑑 = new Entry(new EntryNumber("7892"),
            new Title("思考法図鑑"),
            new WorkOf("株式会社アンド"),
            EntryType.図書);

    Entry あなたの絵に物語性を与える方法 = new Entry(new EntryNumber("7892"),
            new Title("あなたの絵に物語性を与える方法"),
            new WorkOf("加藤 オズワルド"),
            EntryType.図書);

    Entry はじめてのテストプロセス改善 = new Entry(new EntryNumber("7892"),
            new Title("はじめてのテストプロセス改善"),
            new WorkOf("高木 陽平"),
            EntryType.図書);

    Entry 超ど素人がはじめる投資信託 = new Entry(new EntryNumber("7892"),
            new Title("超ど素人がはじめる投資信託"),
            new WorkOf("上本 敏雅"),
            EntryType.図書);

    Entry 独習Git = new Entry(new EntryNumber("7892"),
            new Title("独習Git"),
            new WorkOf("吉川邦夫"),
            EntryType.図書);

    Entry 予想どおりに不合理 = new Entry(new EntryNumber("7892"),
            new Title("予想どおりに不合理"),
            new WorkOf("ダン アリエリー"),
            EntryType.図書);

    Entry AI人材の育て方 = new Entry(new EntryNumber("7892"),
            new Title("AI人材の育て方"),
            new WorkOf("孝忠 大輔"),
            EntryType.図書);

    Entry 耳をすませば = new Entry(new EntryNumber("1993"),
            new Title("耳をすませば"),
            new WorkOf("スタジオ・ジブリ"),
            EntryType.視聴覚資料);
    Entry オペラ座の怪人 = new Entry(new EntryNumber("2003"),
            new Title("オペラ座の怪人"),
            new WorkOf("Andrew Lloyd Webber"),
            EntryType.視聴覚資料);

    Entry ゴッドファーザー = new Entry(new EntryNumber("1972"),
            new Title("ゴッドファーザー"),
            new WorkOf("Francis Ford Coppola"),
            EntryType.視聴覚資料);

    Entry ゴッドファーザー2 = new Entry(new EntryNumber("1974"),
            new Title("ゴッドファーザー2"), new WorkOf("Francis Ford Coppola"),
            EntryType.視聴覚資料);

    Entry ゴッドファーザー3 = new Entry(new EntryNumber("1974"),
            new Title("ゴッドファーザー3"),
            new WorkOf("Francis Ford Coppola"),
            EntryType.視聴覚資料);
}