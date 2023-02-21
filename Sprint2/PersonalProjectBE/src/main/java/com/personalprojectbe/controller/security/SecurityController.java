package com.personalprojectbe.controller.security;

import com.personalprojectbe.dto.customer.CustomerDto;
import com.personalprojectbe.dto.request.SignInForm;
import com.personalprojectbe.dto.respone.JwtResponse;
import com.personalprojectbe.jwt.jwt.JwtProvider;
import com.personalprojectbe.jwt.jwt.JwtTokenFilter;
import com.personalprojectbe.jwt.userprincal.AccountPrinciple;
import com.personalprojectbe.entity.Account;
import com.personalprojectbe.entity.Customer;
import com.personalprojectbe.entity.Role;
import com.personalprojectbe.entity.RoleName;
import com.personalprojectbe.service.ICustomerService;
import com.personalprojectbe.service.IRoleService;
import com.personalprojectbe.service.securityImpl.AccountService;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/public")
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

    @PostMapping(value = "/signup")
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
        account.setAvatar("BossTran");
        account.setEncryptPassword(passwordEncoder.encode(customerDto.getEncryptPassword()));
        Set<Role> roles = new HashSet<>();
        Role customerRole = roleService.findByName(RoleName.USER).orElse(new Role());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("letahaphuong@gmail.com");
        message.setTo(customerDto.getEmail(), "haphuong060695@gmail.com");
        String mailSubject = customerDto.getCustomerName() + "đã gửi một tin nhắn";
        String mailContent = "Người gửi: " + "Camera Store" + "\n";
        mailContent += "Sender E-mail: " + "letahaphuong@gmail.com" + "\n";
        mailContent += "Subject: " + "Thư phản hồi" + "\n";
        mailContent += "Content: " + "Chào mừng quí khách đã đến với Camera Store" + "\n";
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

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getEmail(), signInForm.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.createToken(authentication);
        AccountPrinciple accountPrinciple = (AccountPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token,
                accountPrinciple.getName(),
                accountPrinciple.getAuthorities(),
                accountPrinciple.getId(),
                accountPrinciple.getEmail(),
                accountPrinciple.getAvatar()));
    }
}
