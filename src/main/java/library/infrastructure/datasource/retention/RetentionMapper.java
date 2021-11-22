package library.infrastructure.datasource.retention;

import library.domain.model.material.item.ItemNumber;
import library.domain.model.reservation.ReservationNumber;
import library.domain.model.retention.Retained;
import library.domain.model.retention.RetainedDate;
import library.domain.model.retention.RetentionNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RetentionMapper {
    RetentionNumber nextNumber();

    void insert取置(
            @Param("retentionNumber") RetentionNumber retentionNumber,
            @Param("reservationNumber") ReservationNumber reservationNumber,
            @Param("itemNumber") ItemNumber itemNumber,
            @Param("retainedDate") RetainedDate retainedDate);

    void insert準備完了(
            @Param("retentionNumber") RetentionNumber retentionNumber);

    List<Retained> selectAll準備完了();

    Retained select準備完了(ItemNumber itemNumber);
    void delete準備完了(RetentionNumber retentionNumber);

    void insert取置解放(RetentionNumber retentionNumber);

    void insert取置期限切れ(RetentionNumber retentionNumber);

    boolean exists準備完了(RetentionNumber retentionNumber);
    boolean exists取置解放(RetentionNumber retentionNumber);
}
