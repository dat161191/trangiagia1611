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
        String mailContent = "Ng?????i g???i: " + "Shop Clock" + "\n";
        mailContent += "Sender E-mail: " + "shopclock@gmail.com" + "\n";
        mailContent += "Subject: " + "Th?? ph???n h???i" + "\n";
        mailContent += "Content: " + "Ch??o m???ng qu?? kh??ch ???? ?????n v???i Shop Clock" + "\n";
        mailContent += "Username: " + customerDto.getEmail() + "\n";
        mailContent += "Password: " + customerDto.getEncryptPassword() + "\n";
        mailContent += "Content: " + "Vui l??ng ????ng nh???p ????? ti???p t???c." + "\n";
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
     * Ph????ng th???c tr??n ??ang th???c hi???n x??c th???c (authentication) ng?????i d??ng (user) b???ng c??ch l???y th??ng tin ????ng nh???p t??? ?????i t?????ng signInForm g???m email v?? password.
     * N?? t???o ra m???t ?????i t?????ng UsernamePasswordAuthenticationToken v???i th??ng tin ????ng nh???p v?? truy???n n?? v??o ph????ng th???c authenticate c???a ?????i t?????ng authenticationManager.
     * Ph????ng th???c authenticate s??? s??? d???ng c??c AuthenticationProvider ???????c c???u h??nh ????? x??c th???c ng?????i d??ng v?? tr??? v??? ?????i t?????ng Authentication ch???a th??ng tin x??c th???c c???a ng?????i d??ng n???u x??c th???c th??nh c??ng.
     * N???u kh??ng th??nh c??ng, n?? s??? n??m m???t ngo???i l??? AuthenticationException.
     * K???t qu??? x??c th???c ???????c tr??? v??? ???????c g??n cho ?????i t?????ng authentication.
     *  @param signInForm
     * @return
     */
    @PostMapping("public/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getEmail(), signInForm.getPassword())
        );
        /* SecurityContextHolder.getContext().setAuthentication(authentication)
        *  S??? d???ng ????? thi???t l???p th??ng tin x??c th???c c???a ng?????i d??ng ??ang ???????c ????ng nh???p v??o ???ng d???ng.
        *  B???ng c??ch g???i ph????ng th???c setAuthentication() c???a SecurityContextHolder, th??ng tin x??c th???c n??y ???????c l??u tr??? trong b???i c???nh b???o m???t hi???n t???i c???a ???ng d???ng.
        * Sau ????, ???ng d???ng c?? th??? s??? d???ng th??ng tin n??y ????? ki???m tra quy???n truy c???p c???a ng?????i d??ng v??o c??c t??i nguy??n b???o m???t kh??c nhau,
            + ch???ng h???n nh?? trang web, API ho???c truy c???p v??o c?? s??? d??? li???u.*/
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
            account = accountService.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("email kh??ng t???n t???i" + email));
            account.setAvatar(changeAvatar.getAvatar());
            accountService.save(account);

            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);

        } catch (UsernameNotFoundException exception) {
            return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
