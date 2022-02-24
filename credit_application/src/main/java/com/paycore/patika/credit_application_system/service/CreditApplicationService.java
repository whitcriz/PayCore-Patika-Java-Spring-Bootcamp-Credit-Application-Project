package com.paycore.patika.credit_application_system.service;

import com.paycore.patika.credit_application_system.model.entity.CreditApplication;
import com.paycore.patika.credit_application_system.model.entity.Customer;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CreditApplicationService {

    boolean createCreditApplication(@RequestBody CreditApplication creditApplication);

    List<CreditApplication> getAllCreditApplications();

    CreditApplication getCreditApplicationByCustomer(String customerNationalIdentityNumber);

    CreditApplication updateCreditApplication(CreditApplication creditApplication);

}
