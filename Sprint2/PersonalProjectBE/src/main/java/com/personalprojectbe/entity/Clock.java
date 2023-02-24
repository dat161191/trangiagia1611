package com.personalprojectbe.entity;

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
public class Clock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String origin; // Xuất xứ
    @Column(columnDefinition = "bit default false")
    private boolean gender = false;
    private String faceDiameter;//đường kính mặt
    private Integer price;
    private Integer quanlity;
    private String thick;//độ dày
    private String shellMaterial;//chất liệu vỏ
    private String ropeMaterial;//chất liệu dây
    private String glassMaterial;//chất liệu mặt kính
    private String resistanceWater;//do tham nuoc
    private String countryRegistration;//Quốc gia đăng ký thương hiệu
    @Column(columnDefinition = "TEXT")
    private String note;
    @Column(columnDefinition = "bit default false")
    private boolean flag = false;
    @ManyToOne
    @JoinColumn(name = "trademark_id", referencedColumnName = "id")
    private Trademark trademark;//thương hiệu
    @ManyToOne
    @JoinColumn(name = "machineType_id", referencedColumnName = "id")
    private MachineType machineType;//Kiểu máy
}
