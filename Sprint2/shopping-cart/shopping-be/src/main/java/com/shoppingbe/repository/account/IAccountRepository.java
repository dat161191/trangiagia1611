package com.shoppingbe.repository.account;

import com.shoppingbe.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);

    Boolean existsByEmail(String email);
}
