package library.application.coordinator.bookonloan;

/**
 * 貸出図書 検証エラー
 */
// TODO: 列挙として軸が違う。まず貸出可否。
    // 存在チェック、貸出可否に関係ない
public enum BookOnLoanValidResult {
    貸出可能(""),
    存在しない会員番号( "存在しない会員番号です。"),
    存在しない蔵書コード( "存在しない蔵書コードです。"),
    貸出中の蔵書( "現在貸出中の蔵書です。"),
    貸出制限エラー( "これ以上本を貸し出すことができません。");

    String message;

    BookOnLoanValidResult(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }

    public boolean hasError() {
        return this != 貸出可能;
    }
}
