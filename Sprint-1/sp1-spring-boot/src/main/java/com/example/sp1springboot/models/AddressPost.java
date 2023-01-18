package com.example.sp1springboot.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class AddressPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAddress;
    private String numberAddress;
    @OneToMany(mappedBy = "addressPost")
    @JsonBackReference
    private Set<Post> posts;

    @ManyToOne
    @JoinColumn(name = "id_ward")
    private Ward ward;
}
