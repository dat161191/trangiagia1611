package com.conectbemodal.repository;


import com.conectbemodal.model.Consignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IConsignmentRepository extends JpaRepository<Consignment,Integer> {
    @Query(value = "select * from consignment join product p on p.pro_id = consignment.product_pro_id where p.product_name like %:productName% and  consignment.import_date like %:importDate% and consignment.deleted=false",countQuery = "select * from consignment join product p on p.pro_id = consignment.product_pro_id where p.product_name like %:productName% and consignment.import_date like %:importDate% and consignment.deleted=false",nativeQuery = true)
    Page<Consignment> getList(@Param("productName") String productName, @Param("importDate") String importDate, Pageable pageable);
}
