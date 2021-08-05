package library.domain.model.retention;

import library.domain.model.material.bibliography.Book;

/**
 * 蔵書と本の照合結果
 */
public enum BookMatching {
    一致("この蔵書は該当の資料です"),
    不一致("この蔵書は該当の資料ではありません");

    String description;

    BookMatching(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }

    public static BookMatching isSame(Book one, Book another) {
        return one.isSame(another) ? 一致 : 不一致;
    }
}
