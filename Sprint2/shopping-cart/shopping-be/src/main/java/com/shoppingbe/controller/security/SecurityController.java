package com.shoppingbe.controller.security;

import com.shoppingbe.commons.MyConstants;
import com.shoppingbe.dto.ChangeAvatar.ChangeAvatar;
import com.shoppingbe.dto.customer.CustomerDto;
import com.shoppingbe.dto.customer.GetIdCustomer;
import com.shoppingbe.dto.request.SignInForm;
import com.shoppingbe.dto.respone.JwtResponse;
import com.shoppingbe.dto.respone.ResponseMessage;
import com.shoppingbe.entity.Account;
import com.shoppingbe.entity.Customer;
import com.shoppingbe.entity.Role;
import com.shoppingbe.entity.RoleName;
import com.shoppingbe.jwt.jwt.JwtProvider;
import com.shoppingbe.jwt.jwt.JwtTokenFilter;
import com.shoppingbe.jwt.userprincal.AccountPrinciple;
import com.shoppingbe.service.customer.ICustomerService;
import com.shoppingbe.service.security.IRoleService;
import com.shoppingbe.service.security.securityImpl.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SecurityController {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    AccountService accountService;

    @Autowired
    IRoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    JwtTokenFilter jwtTokenFilter;
    @Autowired
    private ICustomerService customerService;

    @PostMapping(value = "public/register")
    public ResponseEntity<Customer> register(@Valid @RequestBody CustomerDto customerDto,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Customer>((Customer) bindingResult.getFieldErrors(),
                    HttpStatus.BAD_REQUEST);
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        Account account = new Account();
        account.setName(customerDto.getCustomerName());
        account.setEmail(customerDto.getEmail());
        if (customerDto.getAvatar() == null || customerDto.getAvatar().trim().isEmpty()) {
            account.setAvatar("http://hinhnendepnhat.net/wp-content/uploads/2014/03/12+hinh+nen+dien+thoai+tinh+yeu.jpg");
        }
        account.setEncryptPassword(passwordEncoder.encode(customerDto.getEncryptPassword()));
        Set<Role> roles = new HashSet<>();
        Role customerRole = roleService.findByName(RoleName.ROLE_USER).orElse(new Role());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("quocdat.tran453@gmail.com");
        message.setTo(customerDto.getEmail());
        String mailSubject = MyConstants.SEND_MAIL_REGISTER;
        String mailContent = "Người gửi: " + "Shop Clock" + "\n";
        mailContent += "Sender E-mail: " + "shopclock@gmail.com" + "\n";
        mailContent += "Subject: " + "Thư phản hồi" + "\n";
        mailContent += "Content: " + "Chào mừng quí khách đã đến với Shop Clock" + "\n";
        mailContent += "Username: " + customerDto.getEmail() + "\n";
        mailContent += "Password: " + customerDto.getEncryptPassword() + "\n";
        mailContent += "Content: " + "Vui lòng đăng nhập để tiếp tục." + "\n";
        message.setSubject(mailSubject);
        message.setText(mailContent);
        javaMailSender.send(message);

        roles.add(customerRole);
        account.setRoles(roles);
        accountService.save(account);
        customer.setAccount(account);
        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *  Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInForm.getEmail(), signInForm.getPassword()):
     * Phương thức trên đang thực hiện xác thực (authentication) người dùng (user) bằng cách lấy thông tin đăng nhập từ đối tượng signInForm gồm email và password.
     * Nó tạo ra một đối tượng UsernamePasswordAuthenticationToken với thông tin đăng nhập và truyền nó vào phương thức authenticate của đối tượng authenticationManager.
     * Phương thức authenticate sẽ sử dụng các AuthenticationProvider được cấu hình để xác thực người dùng và trả về đối tượng Authentication chứa thông tin xác thực của người dùng nếu xác thực thành công.
     * Nếu không thành công, nó sẽ ném một ngoại lệ AuthenticationException.
     * Kết quả xác thực được trả về được gán cho đối tượng authentication.
     *  @param signInForm
     * @return
     */
    @PostMapping("public/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getEmail(), signInForm.getPassword())
        );
        /* SecurityContextHolder.getContext().setAuthentication(authentication)
        *  Sử dụng để thiết lập thông tin xác thực của người dùng đang được đăng nhập vào ứng dụng.
        *  Bằng cách gọi phương thức setAuthentication() của SecurityContextHolder, thông tin xác thực này được lưu trữ trong bối cảnh bảo mật hiện tại của ứng dụng.
        * Sau đó, ứng dụng có thể sử dụng thông tin này để kiểm tra quyền truy cập của người dùng vào các tài nguyên bảo mật khác nhau,
            + chẳng hạn như trang web, API hoặc truy cập vào cơ sở dữ liệu.*/
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.createToken(authentication);
        AccountPrinciple accountPrinciple = (AccountPrinciple) authentication.getPrincipal();
        Optional<GetIdCustomer> idCustomer = customerService.findIdCustomer(signInForm.getEmail());
        return ResponseEntity.ok(new JwtResponse(token,
                accountPrinciple.getName(),
                accountPrinciple.getAuthorities(),
                accountPrinciple.getId(),
                accountPrinciple.getEmail(),
                accountPrinciple.getAvatar(),
                accountPrinciple.getEncoder(),
                idCustomer
        ));
    }

    @PutMapping("/pulic/change-avatar")
    public ResponseEntity<?> changeAvatar(HttpServletRequest request, @Valid @RequestBody ChangeAvatar changeAvatar) {
        String jwt = jwtTokenFilter.getJwt(request);
        String email = jwtProvider.getUserNameFromToken(jwt);
        Account account;
        try {
            if (changeAvatar.getAvatar() == null) {
                return new ResponseEntity<>(new ResponseMessage("no"), HttpStatus.ACCEPTED);
            }
            account = accountService.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("email không tồn tại" + email));
            account.setAvatar(changeAvatar.getAvatar());
            accountService.save(account);

            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);

        } catch (UsernameNotFoundException exception) {
            return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
