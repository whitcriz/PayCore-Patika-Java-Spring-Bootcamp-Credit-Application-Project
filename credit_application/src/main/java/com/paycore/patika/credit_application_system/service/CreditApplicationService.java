package com.paycore.patika.credit_application_system.service;

import com.paycore.patika.credit_application_system.model.entity.CreditApplication;
import com.paycore.patika.credit_application_system.model.entity.Customer;

import java.util.List;

public interface CreditApplicationService {

    List<CreditApplication> getAllCreditApplicationsByCustomer(Customer customer);

    String createCreditApplication(Customer customer);

    CreditApplication getActiveCreditApplicationByCustomer(Customer customer);

    void UpdateNotResultedApplication(Customer customer);

    boolean deleteCreditApplication(Customer customer);


}
