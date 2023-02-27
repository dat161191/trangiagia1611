package com.personalprojectbe.service.securityImpl;

import com.personalprojectbe.entity.Customer;
import com.personalprojectbe.repository.account.ICustomerrRpository;
import com.personalprojectbe.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerrRpository customerrRpository;

    @Override
    public Customer save(Customer customer) {
        return customerrRpository.save(customer);
    }
}
