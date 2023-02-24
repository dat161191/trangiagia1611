package com.personalprojectbe.repository.product;

import com.personalprojectbe.dto.clock.ImgDto;
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
    @Query(value = "SELECT img.id as idImage, img.url url from img where img.clock_id=:clockId", nativeQuery = true)
    List<ImgDto> findByIdClock(@Param("clockId") Long clockId);
    @Modifying
    @Query(value = "insert into img (url, product_id) values (:url, :idProduct)",nativeQuery = true)
    @Transactional
    void saveImage(@Param("url") String url, @Param("id") Long idProduct);
}
