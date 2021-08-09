package library.domain.model.material.entry;

/**
 * 著者・アーティスト
 */
public class WorkOf {
    String value;

    public WorkOf(String value) {
        this.value = value;
    }

    @Deprecated
    WorkOf() {
    }

    @Override
    public String toString() {
        return value;
    }
}
