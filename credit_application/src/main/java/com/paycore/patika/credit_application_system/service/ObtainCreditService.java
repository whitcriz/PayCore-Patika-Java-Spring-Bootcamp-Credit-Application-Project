package com.paycore.patika.credit_application_system.service;

import com.paycore.patika.credit_application_system.model.entity.CreditApplication;

public interface ObtainCreditService {

    boolean obtainCreditByCreditApplication(CreditApplication creditApplication);
}
