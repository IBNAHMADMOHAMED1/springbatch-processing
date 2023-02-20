package com.batchprocessing.demo.repository;

import com.batchprocessing.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRespo extends JpaRepository<Customer, Long> {
}
