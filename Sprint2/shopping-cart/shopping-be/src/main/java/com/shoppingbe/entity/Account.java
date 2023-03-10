package com.shoppingbe.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Account", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAccount;

    private String name;

    @Lob // chuỗi văn bản dài
    private String avatar;

    private String email;

    private String encryptPassword;

    @Column(columnDefinition = "bit default false")
    private boolean flagDelete;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_role", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    //test
    private String encoder;
    //
    public Account() {
    }

//    public Account(Long idAccount, String name, String email, String avatar, String encryptPassword, boolean flagDelete, Set<Role> roles) {
//        this.idAccount = idAccount;
//        this.name = name;
//        this.avatar = avatar;
//        this.email = email;
//        this.encryptPassword = encryptPassword;
//        this.flagDelete = flagDelete;
//        this.roles = roles;
//    }


    public Account(Long idAccount, String name, String avatar, String email, String encryptPassword, boolean flagDelete, Set<Role> roles, String encoder) {
        this.idAccount = idAccount;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.encryptPassword = encryptPassword;
        this.flagDelete = flagDelete;
        this.roles = roles;
        this.encoder = encoder;
    }

    //Test
    public String getEncoder() {
        return encoder;
    }

    public void setEncoder(String encoder) {
        this.encoder = encoder;
    }
    //Test
    public Account(String name, String email, String encode) {
        this.name = name;
        this.email = email;
        this.encryptPassword = encode;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncryptPassword() {
        return encryptPassword;
    }

    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
