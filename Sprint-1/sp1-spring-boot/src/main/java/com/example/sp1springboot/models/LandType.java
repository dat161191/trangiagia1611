package com.example.sp1springboot.models;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "land_type")
public class LandType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLandType ;
    private String nameLandType  ;
}
