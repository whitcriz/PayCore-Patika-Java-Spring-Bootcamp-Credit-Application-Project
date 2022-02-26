package com.paycore.patika.credit_application_system.repository;

import com.paycore.patika.credit_application_system.model.entity.CreditApplication;
import com.paycore.patika.credit_application_system.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CreditApplicationRepository extends JpaRepository<CreditApplication,Integer> {

}
