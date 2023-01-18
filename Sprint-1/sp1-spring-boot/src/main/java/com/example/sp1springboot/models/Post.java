package com.example.sp1springboot.models;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE post SET flag_deleted = true WHERE id_post=?")
@Where(clause = "flag_deleted=false")
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;
    private String namePost;
    private Double area;
    private String notePost;
    private String descriptionPost;
    private Double price;
    private boolean flagDeleted = false;
    private boolean approval = false;
    @Column(columnDefinition = "DATETIME")
    private String dateCreation;
    @ManyToOne
    @JoinColumn(name = "id_status_post")
    private StatusPost statusPost;
    @ManyToOne
    @JoinColumn(name = "id_address_post")
    private AddressPost addressPost;
    @ManyToOne
    @JoinColumn(name = "id_land_type ")
    private LandType landType;
    @ManyToOne
    @JoinColumn(name = "id_images ")
    private Images images;
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "id_direction")
    private Direction direction;
    @ManyToOne
    @JoinColumn(name = "id_demand_type")
    private DemandType demandType;
}
