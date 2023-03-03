package com.shoppingbe.dto.respone;


import com.shoppingbe.dto.customer.GetIdCustomer;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Optional;

public class JwtResponse {
    private Long id;
    private String token;
    private String type = "Bearer";
    private String name;
    private String avatar;
    private String email;

    private Optional<GetIdCustomer> idCustomer;
    private Collection<? extends GrantedAuthority> roles;
    //TEST=============================
    private String encoder;


    public String getEncoder() {
        return encoder;
    }

    public void setEncoder(String encoder) {
        this.encoder = encoder;
    }

    //========================================
    public JwtResponse() {
    }

    public JwtResponse(Long id, String token, String type, String name, String avatar, String email, Collection<? extends GrantedAuthority> roles, String encoder) {
        this.id = id;
        this.token = token;
        this.type = type;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.roles = roles;
        this.encoder = encoder;
    }

    public JwtResponse(String token, String name, Collection<? extends GrantedAuthority> authorities, Long id, String email, String avatar,String encoder,Optional<GetIdCustomer> idCustomer) {
        this.token = token;
        this.name = name;
        this.roles = authorities;
        this.id = id;
        this.email = email;
        this.avatar = avatar;
        this.encoder=encoder;
        this.idCustomer = idCustomer;
    }

    public Optional<GetIdCustomer> getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Optional<GetIdCustomer> idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }
}
