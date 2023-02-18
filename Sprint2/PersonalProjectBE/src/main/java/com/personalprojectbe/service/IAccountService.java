package com.personalprojectbe.service;

import com.personalprojectbe.model.Account;

import java.util.Optional;

public interface IAccountService {
    Optional<Account> findByEmail(String email);

    Boolean existsByEmail(String email);

    Account save(Account account);
}
