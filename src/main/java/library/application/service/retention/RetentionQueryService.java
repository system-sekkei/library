package library.application.service.retention;

import library.application.repository.CounterRepository;
import library.application.repository.HoldingRepository;
import library.domain.model.book.BookId;
import library.domain.model.counter.Counter;
import library.domain.model.holding.HoldingsInStock;
import library.domain.model.reservation.reservation.Reservations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 取置参照サービス
 */
@Service
public class RetentionQueryService {
    HoldingRepository holdingRepository;
    CounterRepository counterRepository;

    public RetentionQueryService(HoldingRepository holdingRepository, CounterRepository counterRepository) {
        this.holdingRepository = holdingRepository;
        this.counterRepository = counterRepository;
    }

    public Counter counter(Reservations reservations) {
        HoldingsInStock holdingsInStock =
            holdingRepository.findHoldingsInStockByBookIds(reservations.bookIds());

        List<Counter> list = new ArrayList<>();
        for (BookId bookId : reservations.bookIds().asList()) {
            list.add(counterRepository.counters(bookId));
        }

        // TODO: Counter生成
        return null;
    }
}
