package com.shoppingbe.service;


import com.shoppingbe.dto.customer.GetIdCustomer;
import com.shoppingbe.entity.Customer;

import java.util.Optional;

public interface ICustomerService {
    Customer save(Customer customer);
    Optional<GetIdCustomer> findIdCustomer(String email);

}
