package com.personalprojectbe.repository.account;

import com.personalprojectbe.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerrRpository extends JpaRepository<Customer,Long> {
}
