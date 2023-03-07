package com.shoppingbe.service.customer;


import com.shoppingbe.dto.customer.GetIdCustomer;
import com.shoppingbe.entity.Clock;
import com.shoppingbe.entity.Customer;

import java.util.Optional;

public interface ICustomerService {
    Customer save(Customer customer);
    Optional<GetIdCustomer> findIdCustomer(String email);

    /**
     * 04/03/2023 by BossTran
     * @param idAccount
     * @return
     */
    Customer findByAccount_IdAccount(Long idAccount);
    Customer findById(Long id);

}
