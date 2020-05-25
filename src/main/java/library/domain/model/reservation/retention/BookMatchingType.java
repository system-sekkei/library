package library.domain.model.reservation.retention;

/**
 * 蔵書と本の照合結果
 */
public enum BookMatchingType {
    一致("この蔵書は該当の書籍です"),
    不一致("この蔵書は該当の書籍ではありません");

    String description;

    BookMatchingType(String description) {
        this.description = description;
    }

    String description() {
        return description;
    }
}
