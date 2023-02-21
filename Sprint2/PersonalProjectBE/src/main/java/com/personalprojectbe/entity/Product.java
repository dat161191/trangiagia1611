package com.personalprojectbe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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
    private String stiffNess;// Độ cứng
    private String racquetFrame; // Khung vợt
    private String racketShaft; // Thân vợt
    private String gripCircumference; // Chu vi cán vợt
    private String tension; // Sức căng
    private String color;
    private String weight;
    private Integer quanlity;
    private Integer price;
    private String racketLength; //Chiều dài vợt
    private String balancedPoint; // Điểm cân bằng
    private String origin; // Xuất xứ
    @OneToMany(mappedBy = "product")
    @JsonBackReference
    private Set<Img> imgs;
    @ManyToOne
    @JoinColumn(name = "producer_id", referencedColumnName = "id")
    private Producer producer;
}
