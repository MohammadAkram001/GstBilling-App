package com.invoice.gstbilling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.gstbilling.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
