package library.domain.model.retention;

import library.domain.model.material.entry.Material;

/**
 * 蔵書と本の照合結果
 */
public enum MaterialMatching {
    一致("この蔵書は該当の資料です"),
    不一致("この蔵書は該当の資料ではありません");

    String description;

    MaterialMatching(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }

    public static MaterialMatching isSame(Material one, Material another) {
        return one.isSame(another) ? 一致 : 不一致;
    }
}
