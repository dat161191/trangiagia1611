package com.personalprojectbe.dto.customer;

import com.personalprojectbe.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomerDto {
    private Long id;
    @NotBlank(message = "Không được để trống.")
    private String customerName;
    @NotBlank(message = "Không được để trống.")
    private String email;
    @NotBlank(message = "Không được để trống.")
    private String idCard;
    @NotBlank(message = "Không được để trống.")
    private String phone;
    @NotBlank(message = "Không được để trống.")
    private String addressCustomer;
    @Column(columnDefinition = "bit")
    private boolean gender;
    private String birthday;
    private Account account;
    @Column(columnDefinition = "bit default false")
    private boolean flagDelete;
    /*Chuỗi mã hóa đưa xuống DB*/
    private String encryptPassword;
    private String avatar;
}
