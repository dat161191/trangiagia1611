package com.personalprojectbe.jwt.userprincal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.personalprojectbe.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AccountPrinciple implements UserDetails {
    private Long id;
    private String name;
    private String email;
    @JsonIgnore
    private String encryptPassword;
    private String avatar;
    private Collection<? extends GrantedAuthority> roles;

    //Test===============================
    private String encoder;

    public String getEncoder() {
        return encoder;
    }

    public void setEncoder(String encoder) {
        this.encoder = encoder;
    }
//====================================


    /**
     * Về mặt chức năng, đoạn mã này chuyển đổi các đối tượng Role thành các đối tượng GrantedAuthority, và trả về danh sách các đối tượng GrantedAuthority này.
     * Điều này có thể được sử dụng để xác định các quyền của tài khoản trong ứng dụng.
     * Cụ thể, đối tượng GrantedAuthority chứa thông tin về quyền được phép trong ứng dụng, được sử dụng để xác định quyền truy cập vào các tài nguyên khác nhau trong ứng dụng.
     * Phương thức getAuthorities() được định nghĩa trong giao diện UserDetails của Spring Security.
     * Nó được sử dụng để trả về danh sách các đối tượng GrantedAuthority mà người dùng đang được phân quyền trong ứng dụng.
     * Trong đoạn mã này, phương thức getAuthorities() trả về danh sách các đối tượng GrantedAuthority được lưu trữ trong thuộc tính roles.
     * Điều này cho phép Spring Security biết các quyền được cấp cho người dùng và xác định xem người dùng có được phép truy cập vào các tài nguyên nào trong ứng dụng.
     * Trong phương thức getAuthorities() này có thể trả về danh sách các đối tượng GrantedAuthority được lưu trữ trong một thuộc tính của đối tượng người dùng.
     * Nó cũng có thể được thay đổi để lấy danh sách GrantedAuthority từ bất kỳ nguồn dữ liệu nào, chẳng hạn như cơ sở dữ liệu hoặc tệp cấu hình.
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public AccountPrinciple() {
    }

    public AccountPrinciple(Long id, String name, String email, String encryptPassword, String avatar, Collection<? extends GrantedAuthority> roles, String encoder) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.encryptPassword = encryptPassword;
        this.avatar = avatar;
        this.roles = roles;
        this.encoder = encoder;
    }

    /*Build Account mới dưới DB*/

    /**
     * Nhận đối tượng Account và trả về một đối tượng AccountPrinciple.
     * Đối tượng AccountPrinciple là một lớp được định nghĩa trong ứng dụng của bạn để đại diện cho người dùng đăng nhập vào hệ thống.
     * Thuộc tính authorities này được sử dụng để xác định quyền truy cập của người dùng đến các tài nguyên khác nhau trong ứng dụng.
     *
     * @param account
     * @return Phương thức trả về một đối tượng AccountPrinciple được khởi tạo từ các thuộc tính của đối tượng Account và danh sách authorities được tạo ra từ danh sách các vai trò của người dùng trong đối tượng Account.
     */
    public static AccountPrinciple build(Account account) {// build account trên hệ thống
        /**
         * Đây là một đoạn mã Java sử dụng Java 8 Stream API để chuyển đổi danh sách các đối tượng Role của tài khoản thành danh sách các đối tượng GrantedAuthority.
         * Giải thích từng phần của đoạn mã:
         * account.getRoles() trả về danh sách các đối tượng Role của tài khoản.
         * .stream() chuyển đổi danh sách thành một luồng Stream.
         * .map(role -> new SimpleGrantedAuthority(role.getName().name())) sử dụng phương thức map để chuyển đổi mỗi đối tượng Role thành một đối tượng GrantedAuthority.
         * Cụ thể, phương thức map áp dụng hàm role -> new SimpleGrantedAuthority(role.getName().name()) cho mỗi đối tượng Role.
         * Hàm này sử dụng tên của mỗi Role để tạo một đối tượng GrantedAuthority mới bằng cách sử dụng lớp SimpleGrantedAuthority.
         * .collect(Collectors.toList()) thu thập kết quả của toàn bộ luồng Stream thành một danh sách List.
         * Về mặt chức năng, đoạn mã này chuyển đổi các đối tượng Role thành các đối tượng GrantedAuthority, và trả về danh sách các đối tượng GrantedAuthority này.
         * Điều này có thể được sử dụng để xác định các quyền của tài khoản trong ứng dụng.
         * Cụ thể, đối tượng GrantedAuthority chứa thông tin về quyền được phép trong ứng dụng, được sử dụng để xác định quyền truy cập vào các tài nguyên khác nhau trong ứng dụng.
         */
        List<GrantedAuthority> authorities = account.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new AccountPrinciple(
                account.getIdAccount(),
                account.getName(),
                account.getEmail(),
                account.getEncryptPassword(),
                account.getAvatar(),
                authorities,
                account.getEncoder()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }


    @Override
    public String getPassword() {
        return encryptPassword;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
