package com.personalprojectbe.repository.product;

import com.personalprojectbe.dto.clock.ClockHomeDto;
import com.personalprojectbe.entity.Clock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClockRepository extends JpaRepository<Clock, Long> {
    @Query(value = "select c.id clockId,c.name clockName,c.price price,i.url url,t.id trademarkId,i.id imgId,mt.id machineTypeId,c.gender gender,c.flag flag from clock c left join img i on c.id = i.clock_id join trademark t on t.id = c.trademark_id join machine_type mt on mt.id = c.machine_type_id where flag=false group by c.id",
            countQuery = "select c.id clockId,c.name clockName,c.price price,i.url url,t.id trademarkId,i.id imgId,mt.id machineTypeId,c.gender gender,c.flag flag from clock c left join img i on c.id = i.clock_id join trademark t on t.id = c.trademark_id join machine_type mt on mt.id = c.machine_type_id where flag=false group by c.id", nativeQuery = true)
    Page<ClockHomeDto> getListClock(Pageable pageable);

    @Query(value = "select c.id clockId,c.name clockName,c.price price,i.url url,t.id trademarkId,i.id imgId,mt.id machineTypeId,c.gender gender,c.flag flag from clock c left join img i on c.id = i.clock_id join trademark t on t.id = c.trademark_id join machine_type mt on mt.id = c.machine_type_id where flag=false and trademark_id=:tradeMarkId group by c.id", nativeQuery = true)
    List<ClockHomeDto> getListByTrademarkId(@Param("tradeMarkId") Long tradeMarkId);
}