package com.shoppingbe.service.securityImpl;

import com.shoppingbe.dto.customer.GetIdCustomer;
import com.shoppingbe.entity.Customer;
import com.shoppingbe.repository.account.ICustomerrRpository;
import com.shoppingbe.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerrRpository customerrRpository;

    @Override
    public Customer save(Customer customer) {
        return customerrRpository.save(customer);
    }

    @Override
    public Optional<GetIdCustomer> findIdCustomer(String email) {
        return customerrRpository.findIdCustomer(email);
    }
}
