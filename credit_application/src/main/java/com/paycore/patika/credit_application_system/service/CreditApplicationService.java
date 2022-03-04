package com.paycore.patika.credit_application_system.service;

import com.paycore.patika.credit_application_system.model.entity.CreditApplication;
import com.paycore.patika.credit_application_system.model.entity.Customer;

import java.util.List;

public interface CreditApplicationService {

    String createCreditApplication(Customer customer);

    CreditApplication getActiveAndApprovedCreditApplicationByCustomer(Customer customer);

    boolean isThereAnyActiveApplicationByCustomer(Customer customer);

    void UpdateNotResultedApplication(Customer customer);

    boolean deleteCreditApplication(Customer customer);


}
