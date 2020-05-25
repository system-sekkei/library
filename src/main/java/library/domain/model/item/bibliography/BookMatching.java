package library.domain.model.item.bibliography;

/**
 * 蔵書と本の照合
 */
public enum BookMatching {
    一致("この蔵書は該当の書籍です"),
    不一致("この蔵書は該当の書籍ではありません");

    String description;

    BookMatching(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }
}
