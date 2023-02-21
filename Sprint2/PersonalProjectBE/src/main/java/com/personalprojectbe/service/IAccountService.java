package com.personalprojectbe.service;

import com.personalprojectbe.entity.Account;

import java.util.Optional;

public interface IAccountService {
    Optional<Account> findByEmail(String email);

    Boolean existsByEmail(String email);

    Account save(Account account);
}
