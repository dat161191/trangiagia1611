package com.shoppingbe.dto.clock;
import com.shoppingbe.entity.MachineType;
import com.shoppingbe.entity.Trademark;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClockCreateDto {
    private Long id;
    @NotBlank(message = "Không được để trống.")
    private String name;
    @NotBlank(message = "Không được để trống.")
    private String origin; // Xuất xứ
    private boolean gender = false;
    @NotBlank(message = "Không được để trống.")
    private String faceDiameter;//đường kính mặt
    private Integer price;

    private Integer quanlity;
    @NotBlank(message = "Không được để trống.")
    private String thick;
    @NotBlank(message = "Không được để trống.")
    private String shellMaterial;
    @NotBlank(message = "Không được để trống.")
    private String ropeMaterial;
    @NotBlank(message = "Không được để trống.")
    private String glassMaterial;
    @NotBlank(message = "Không được để trống.")
    private String resistanceWater;
    @NotBlank(message = "Không được để trống.")
    private String countryRegistration;
    private Trademark trademark;
    private MachineType machineType;
}
