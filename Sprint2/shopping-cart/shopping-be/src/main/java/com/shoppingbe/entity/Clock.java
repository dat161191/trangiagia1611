package com.shoppingbe.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@DynamicUpdate
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
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "edit_date")
    private Date editDate;
    @Column(columnDefinition = "bit default false")
    private boolean flag = false;
    @ManyToOne
    @JoinColumn(name = "trademark_id", referencedColumnName = "id")
    private Trademark trademark;//thương hiệu
    @ManyToOne
    @JoinColumn(name = "machineType_id", referencedColumnName = "id")
    private MachineType machineType;//Kiểu máy
}
