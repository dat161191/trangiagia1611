package com.personalprojectbe.repository.account;

import com.personalprojectbe.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerrRpository extends JpaRepository<Customer,Long> {
}
