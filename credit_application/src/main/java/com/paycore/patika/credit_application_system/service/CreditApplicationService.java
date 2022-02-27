package com.paycore.patika.credit_application_system.service;

import com.paycore.patika.credit_application_system.model.entity.CreditApplication;
import com.paycore.patika.credit_application_system.model.entity.Customer;

import java.util.List;

public interface CreditApplicationService {

    boolean createCreditApplication(Customer customer);

    CreditApplication getActiveAndApprovedCreditApplicationByCustomer(Customer customer);

    boolean isThereAnyActiveAndApprovedApplicationByCustomer(Customer customer);

    void UpdateNotResultedApplication(Integer creditScore, String nationalIdentityNumber);

    boolean deleteCreditApplication(Customer customer);


}
