package com.personalprojectbe.repository.product;

import com.personalprojectbe.dto.product.ImgDto;
import com.personalprojectbe.entity.Img;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IImgListRepository extends JpaRepository<Img, Long> {
    @Query(value = "SELECT img.id as idImage, img.url from img where img.product_id=:productId", nativeQuery = true)
    List<ImgDto> findByIdProduct(@Param("productId") Long productId);
    @Modifying
    @Query(value = "insert into img (url, product_id) values (:url, :idProduct)",nativeQuery = true)
    @Transactional
    void saveImage(@Param("url") String url, @Param("id") Long idProduct);
}
