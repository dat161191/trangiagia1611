package com.shoppingbe.repository.product;

import com.shoppingbe.dto.clock.ClockHomeDto;
import com.shoppingbe.entity.Clock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClockRepository extends JpaRepository<Clock, Long> {
    @Query(value = "select t.name trademarkName, c.id clockId,c.name clockName,c.price price,i.url url,t.id trademarkId,i.id imgId,mt.id machineTypeId,c.gender gender,c.flag flag" +
            " from clock c left join img i on c.id = i.clock_id" +
            " join trademark t on t.id = c.trademark_id join machine_type mt on mt.id = c.machine_type_id " +
            " where flag=false group by c.id order by c.id", nativeQuery = true)
    List<ClockHomeDto> getListCarousel();

    @Query(value = "select t.name trademarkName, c.id clockId,c.name clockName,c.price price,i.url url,t.id trademarkId,i.id imgId,mt.id machineTypeId,c.gender gender,c.flag flag" +
            " from clock c left join img i on c.id = i.clock_id " +
            "join trademark t on t.id = c.trademark_id join machine_type mt on mt.id = c.machine_type_id " +
            "where flag=false and (c.name like %:search% or t.name like %:search%) group by c.id order by c.id",
            countQuery = "select t.name trademarkName, c.id clockId,c.name clockName,c.price price,i.url url,t.id trademarkId,i.id imgId,mt.id machineTypeId,c.gender gender,c.flag flag " +
                    "from clock c left join img i on c.id = i.clock_id " +
                    "join trademark t on t.id = c.trademark_id " +
                    "join machine_type mt on mt.id = c.machine_type_id " +
                    "where flag=false and (c.name like %:search% or t.name like %:search%) group by c.id order by c.id", nativeQuery = true)
    Page<ClockHomeDto> getListClock(Pageable pageable, @Param("search") String search);

    @Query(value = "select t.name trademarkName,c.id clockId,c.name clockName,c.price price,i.url url,t.id trademarkId,i.id imgId,mt.id machineTypeId,c.gender gender,c.flag flag from clock c left join img i on c.id = i.clock_id join trademark t on t.id = c.trademark_id join machine_type mt on mt.id = c.machine_type_id where flag=false and trademark_id=:tradeMarkId group by c.id", nativeQuery = true)
    List<ClockHomeDto> getListByTrademarkId(@Param("tradeMarkId") Long tradeMarkId);

    /**
     * 06/03/2023
     * @param idCustomer
     * @return
     */
    @Query(value = "select c.* from cart join clock c on c.id = cart.clock_id where customer_id=:idCustomer and cart.flag=false and cart.status=false", nativeQuery = true)
    List<Clock> findByCustomerId(@Param("idCustomer") Long idCustomer);
}
