package com.shoppingbe.repository.account;

import com.shoppingbe.dto.customer.GetIdCustomer;
import com.shoppingbe.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerrRpository extends JpaRepository<Customer, Long> {
    @Query(value = "select id as idCustomer from customer where flag_delete = false and email = :email", nativeQuery = true)
    Optional<GetIdCustomer> findIdCustomer(String email);
}
