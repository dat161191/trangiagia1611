package com.example.sp1springboot.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class DemandType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDemandType;
    private String nameDemandType;
}
