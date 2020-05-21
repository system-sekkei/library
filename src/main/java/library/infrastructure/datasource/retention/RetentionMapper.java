package library.infrastructure.datasource.retention;

import library.domain.model.item.ItemNumber;
import library.domain.model.reservation.reservation.ReservationNumber;
import library.domain.model.reservation.retention.Retained;
import library.domain.model.reservation.retention.RetainedDate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RetentionMapper {

    void insert取置依頼中(ReservationNumber reservationNumber);

    void delete取置依頼中(ReservationNumber reservationNumber);

    void insert取置履歴(
            @Param("reservationNumber") ReservationNumber reservationNumber,
            @Param("itemNumber") ItemNumber itemNumber,
            @Param("retainedDate") RetainedDate retainedDate);

    void insert取置済(
            @Param("reservationNumber") ReservationNumber reservationNumber,
            @Param("itemNumber") ItemNumber itemNumber,
            @Param("retainedDate") RetainedDate retainedDate);

    void delete取置済(ReservationNumber reservationNumber);

    List<Retained> select取置済();
}
