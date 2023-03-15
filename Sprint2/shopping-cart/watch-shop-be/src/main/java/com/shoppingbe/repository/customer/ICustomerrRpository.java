package com.shoppingbe.repository.customer;

import com.shoppingbe.dto.customer.GetIdCustomer;
import com.shoppingbe.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerrRpository extends JpaRepository<Customer, Long> {
    /**
     * 03/03/2023 by BossTran
     * @param email
     * @return
     */
    @Query(value = "select id as idCustomer from customer where flag_delete = false and email = :email", nativeQuery = true)
    Optional<GetIdCustomer> findIdCustomer(String email);

    /**
     * 04/03/2023 by BossTran
     * @param idAccount
     * @return
     */
    Customer findByAccount_IdAccount(Long idAccount);
}
