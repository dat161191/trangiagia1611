package com.example.sp1springboot.models;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDistrict;
    private String nameDistrict;
    @ManyToOne
    @JoinColumn(name = "id_city ")
    private City city;

}
