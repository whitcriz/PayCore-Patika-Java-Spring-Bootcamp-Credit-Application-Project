package com.paycore.patika.credit_application_system.service.impl;

import com.paycore.patika.credit_application_system.model.entity.ApplicationStatus;
import com.paycore.patika.credit_application_system.model.entity.CreditApplication;
import com.paycore.patika.credit_application_system.service.ObtainCreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObtainCreditServiceImpl implements ObtainCreditService {

    @Override
    public String obtainCreditByCreditApplication(CreditApplication creditApplication) {
        creditApplication.setApplicationStatus(ApplicationStatus.PASSIVE);
        return "Approved credit limit " + creditApplication.getCreditLimit() + " TL has transferred to your account.";
    }
}
