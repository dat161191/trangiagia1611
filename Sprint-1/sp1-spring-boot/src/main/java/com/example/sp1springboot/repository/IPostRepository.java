package com.example.sp1springboot.repository;

import com.example.sp1springboot.dto.PostDto;
import com.example.sp1springboot.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPostRepository extends JpaRepository<Post, Long> {
    @Query(value = "select p.id_post idPost,p.price price,p.date_creation dateCreation, sp.id_status_post statusPost,ap.number_address numberPost,w.name_ward nameWard,d.name_district nameDistrict,c.name_city nameCity,year(p.date_creation) yearPost,month(p.date_creation) monthPost" +
            " from post p join address_post ap on ap.id_address = p.id_address_post join ward w on ap.id_ward = w.id_ward join district d on w.id_district = d.id_district join city c on d.id_city = c.id_city join status_post sp on sp.id_status_post = p.id_status_post" +
            " where p.flag_deleted = false order by p.date_creation DESC", nativeQuery = true)
    List<PostDto> getAll();

    @Query(value = "select p.id_post idPost,p.price price,p.date_creation dateCreation, sp.id_status_post statusPost,ap.number_address numberPost,w.name_ward nameWard,d.name_district nameDistrict,c.name_city nameCity,year(p.date_creation) yearPost,month(p.date_creation) monthPost" +
            " from post p join address_post ap on ap.id_address = p.id_address_post join ward w on ap.id_ward = w.id_ward join district d on w.id_district = d.id_district join city c on d.id_city = c.id_city join status_post sp on sp.id_status_post = p.id_status_post" +
            " where p.flag_deleted = false and year(p.date_creation)= :year ", nativeQuery = true)
    List<PostDto> searchYear(@Param("year") String year);
    @Query(value = "select p.id_post idPost,p.price price,p.date_creation dateCreation, sp.id_status_post statusPost,ap.number_address numberPost,w.name_ward nameWard,d.name_district nameDistrict,c.name_city nameCity,year(p.date_creation) yearPost,month(p.date_creation) monthPost" +
            " from post p join address_post ap on ap.id_address = p.id_address_post join ward w on ap.id_ward = w.id_ward join district d on w.id_district = d.id_district join city c on d.id_city = c.id_city join status_post sp on sp.id_status_post = p.id_status_post" +
            " where p.flag_deleted = false and year(p.date_creation)= :year and month(p.date_creation)=:month ", nativeQuery = true)
    List<PostDto> searchYearAndMonth(@Param("year") String year,@Param("month") String month);
}
