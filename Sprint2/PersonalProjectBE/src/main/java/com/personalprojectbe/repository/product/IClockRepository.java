package com.personalprojectbe.repository.product;

import com.personalprojectbe.dto.product.ProductGetListDto;
import com.personalprojectbe.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select p.id productId, p.name_product nameProduct,price price,i.id imgId, url url,producer producer,p2.id producerId from product p left join  img i on p.id = i.product_id join producer p2 on p2.id = p.producer_id group by p.id",
            countQuery = "select p.id productId, p.name_product nameProduct,price price,i.id imgId, url url,producer producer,p2.id producerId from product p left join  img i on p.id = i.product_id join producer p2 on p2.id = p.producer_id group by p.id", nativeQuery = true)
    Page<ProductGetListDto> getListProduct(Pageable pageable);
}
