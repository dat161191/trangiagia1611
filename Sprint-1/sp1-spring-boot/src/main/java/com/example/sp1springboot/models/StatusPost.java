package com.example.sp1springboot.models;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "status_post")
public class StatusPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStatusPost;
    private String nameStatusPost;
}
