package com.personalprojectbe.jwt.userprincal;

import com.personalprojectbe.model.Account;
import com.personalprojectbe.repository.account.IAccountRepository;
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
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username).orElseThrow(() -> new
                UsernameNotFoundException("Account không tìm thấy -> email hoặc passwword" + username));
        return AccountPrinciple.build(account); // gọi lại hàm build bên Accountprinciple để build account mới
    }
}
