package com.conectbemodal.model;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE consignment SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Consignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String consignmentCode;
    private Integer quantity;
    @Column(columnDefinition = "date")
    private String importDate;
    @Column(columnDefinition = "date")
    private String productionDate;
    @Column(columnDefinition = "date")
    private String expirationDate;
    @ManyToOne
    private Product product;
    private boolean deleted = false;
}
