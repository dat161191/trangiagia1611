package com.shoppingbe.controller.customer;

import com.shoppingbe.entity.Clock;
import com.shoppingbe.entity.Customer;
import com.shoppingbe.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @GetMapping("/public/customer/detail/{idAccount}")
    public ResponseEntity<Customer> findById(@PathVariable("idAccount") Long idAccount) {
        Customer clock = customerService.findByAccount_IdAccount(idAccount);
        if (clock == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clock, HttpStatus.OK);
    }
}
