package library.domain.model.bookonloan.librarycard;

import library.domain.model.holding.Holding;

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

    public LibraryCard findLibraryCard(Holding holding) {
        return libraryCardList.stream()
            .filter( libraryCard -> libraryCard.holdingCode.sameValue(holding.holdingCode()) )
            .findAny().orElseThrow(() -> new IllegalArgumentException("対象蔵書の貸出履歴が存在しません。 code:" + holding.holdingCode()));
    }
}
