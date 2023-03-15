package com.shoppingbe.service.customer;

import com.shoppingbe.dto.customer.GetIdCustomer;
import com.shoppingbe.entity.Cart;
import com.shoppingbe.entity.Customer;
import com.shoppingbe.repository.customer.ICustomerrRpository;
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

    @Override
    public Customer findByAccount_IdAccount(Long idAccount) {
        return customerrRpository.findByAccount_IdAccount(idAccount);
    }

    @Override
    public Customer findById(Long id) {
        return customerrRpository.findById(id).get();
    }

}
