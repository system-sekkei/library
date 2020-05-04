package library.domain.model.bookonloan.librarycard;

import library.domain.model.item.Item;

import java.util.List;

/**
 * 貸出カード棚
 */
public class LibraryCardShelf {
    List<LibraryCard> libraryCardList;

    public LibraryCardShelf(List<LibraryCard> libraryCardList) {
        this.libraryCardList = libraryCardList;
    }

    public static LibraryCardShelf empty() {
        return new LibraryCardShelf(List.of());
    }

    public LibraryCard findLibraryCard(Item item) {
        return libraryCardList.stream()
            .filter( libraryCard -> libraryCard.itemNumber.sameValue(item.itemNumber()) )
            .findAny().orElseThrow(() -> new IllegalArgumentException("対象蔵書の貸出履歴が存在しません。 code:" + item.itemNumber()));
    }
}
