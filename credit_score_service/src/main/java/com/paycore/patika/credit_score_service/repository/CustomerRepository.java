package com.paycore.patika.credit_score_service.repository;

import com.paycore.patika.credit_score_service.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Optional<Customer> findByNationalIdentityNumber(String nationalIdentityNumber);
}
