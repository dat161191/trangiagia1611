package com.conectbemodal.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE product SET deleted = true WHERE id=?")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer proId;
    private String productName;
    private String origin;
    private String unit;
    private Double price;
    @OneToMany(mappedBy = "product")
    @JsonBackReference
    private Set<Consignment> consignments;
}
