package com.personalprojectbe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameProduct;
    private String stiffness;
    private String racquetFrame;
    private String racketShaft;
    private String gripCircumference;
    private String tension;
    private String color;
    private String weight;
    private Integer quanlity;
    private Integer price;
    private Integer citizenIdentification;

    @ManyToOne
    @JoinColumn(name = "producer_id", referencedColumnName = "id")
    private Producer producer;
    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;
}
