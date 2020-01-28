package library.application.coordinator.bookonloan;

/**
 * 貸出図書 検証エラー
 */
public enum BookOnLoanValidResult {
    正常(""),
    存在しない会員番号( "存在しない会員番号です。"),
    存在しない蔵書コード( "存在しない蔵書コードです。"),
    貸出制限エラー( "これ以上本を貸し出すことができません。");

    String message;

    BookOnLoanValidResult(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }

    public boolean hasError() {
        return this != 正常;
    }
}
