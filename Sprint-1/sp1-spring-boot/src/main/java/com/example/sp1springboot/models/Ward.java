package com.example.sp1springboot.models;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ward")
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idWard;
    private String nameWard;
    @ManyToOne
    @JoinColumn(name = "id_district")
    private District district;
}
