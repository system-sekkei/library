package library.domain.model.reservation;

import library.domain.model.material.entry.*;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.member.MemberType;
import library.domain.model.member.Name;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationsTest {

    @Test
    void 視聴覚資料の冊数() {

        Reservations sut = new Reservations(List.of(
                new Reservation(new ReservationNumber("10001"), member, リファクタリング),
                new Reservation(new ReservationNumber("10002"), member, 耳をすませば),
                new Reservation(new ReservationNumber("10003"), member, オペラ座の怪人),
                new Reservation(new ReservationNumber("10004"), member, ゴッドファーザー),
                new Reservation(new ReservationNumber("10005"), member, ゴッドファーザー2),
                new Reservation(new ReservationNumber("10006"), member, ゴッドファーザー3)
        ));
        NumberOfReservation 視聴覚資料の冊数 = sut.視聴覚資料の冊数();

        assertEquals(5, 視聴覚資料の冊数.value);
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


    Reservations 予約リスト = new Reservations(List.of(
            new Reservation(new ReservationNumber("10001"), member, リファクタリング),
            new Reservation(new ReservationNumber("10002"), member, 耳をすませば),
            new Reservation(new ReservationNumber("10003"), member, オペラ座の怪人),
            new Reservation(new ReservationNumber("10004"), member, ゴッドファーザー),
            new Reservation(new ReservationNumber("10005"), member, ゴッドファーザー2),
            new Reservation(new ReservationNumber("10006"), member, ゴッドファーザー3)
    ));
}