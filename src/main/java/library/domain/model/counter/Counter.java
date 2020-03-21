package library.domain.model.counter;

import library.domain.model.bookonloan.librarycard.LibraryCard;
import library.domain.model.holding.Holding;
import library.domain.model.holding.HoldingStatus;
import library.domain.model.retention.RetainedHolding;

/**
 * カウンター
 */
 // TODO: 名前がいけていないのでなんとかする
public class Counter {
    Holding holding;
    LibraryCard libraryCard;
    RetainedHolding retainedHolding;

    public Counter(Holding holding, LibraryCard libraryCard, RetainedHolding retainedHolding) {
        this.holding = holding;
        this.libraryCard = libraryCard;
        this.retainedHolding = retainedHolding;
    }

    public Counter(Holding holding, LibraryCard libraryCard) {
        this(holding, libraryCard, null);
    }

    public HoldingStatus holdingStatus() {
        if(libraryCard.isLoaning()) {
            return HoldingStatus.貸出中;
        }

        if (retainedHolding != null) {
            return HoldingStatus.取置中;
        }

        return HoldingStatus.在庫中;
    }
}
