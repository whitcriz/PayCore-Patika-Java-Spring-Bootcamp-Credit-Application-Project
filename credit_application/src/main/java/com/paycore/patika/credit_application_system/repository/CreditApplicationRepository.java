package com.paycore.patika.credit_application_system.repository;

import com.paycore.patika.credit_application_system.model.entity.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditApplicationRepository extends JpaRepository<CreditApplication,Integer> {

}
