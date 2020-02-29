package library.domain.model.bookonloan.loaning;

/**
 * メッセージ
 */
public class Message {
    String value;

    public Message(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
