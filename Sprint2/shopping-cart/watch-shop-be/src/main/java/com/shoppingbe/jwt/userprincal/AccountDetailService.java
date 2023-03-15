package com.shoppingbe.jwt.userprincal;

import com.shoppingbe.entity.Account;
import com.shoppingbe.repository.account.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailService implements UserDetailsService {
    @Autowired
    IAccountRepository accountRepository;

    /*Tìm đối tượng trong DB: nếu có thì build đối tương Account mới, không có gởi về lỗi
     * Thông tin Account vẫn giữ nguyên, chỉ thay đổi token gởi lên FE*/
    /**
     * Phương thức loadUserByUsername() trong đoạn mã này được sử dụng để tìm kiếm tài khoản người dùng theo tên đăng nhập (username) được cung cấp.
     * Đối tượng AccountPrinciple này được trả về như một đối tượng UserDetails cho Spring Security, được sử dụng để xác thực và kiểm tra quyền truy cập của người dùng.
     * @param username the username identifying the user whose data is required.
     * @return Nếu tài khoản người dùng được tìm thấy, phương thức sẽ sử dụng đối tượng Account để tạo một đối tượng AccountPrinciple bằng cách gọi phương thức build() trong lớp AccountPrinciple.
     * @throws UsernameNotFoundException Nếu tài khoản người dùng không tồn tại, phương thức sẽ ném ra một ngoại lệ UsernameNotFoundException.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username).orElseThrow(() -> new
                UsernameNotFoundException("Account không tìm thấy -> email hoặc passwword" + username));
        return AccountPrinciple.build(account); // gọi lại hàm build bên Accountprinciple để build account mới
    }
}
