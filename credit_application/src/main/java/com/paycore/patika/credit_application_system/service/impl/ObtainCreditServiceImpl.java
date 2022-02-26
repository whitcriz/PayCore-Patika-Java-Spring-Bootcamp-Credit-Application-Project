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
    public boolean obtainCreditByCreditApplication(CreditApplication creditApplication) {
        System.out.println("Approved credit limit " + creditApplication.getCreditLimit() + " TL has transferred to your account.");
        creditApplication.setApplicationStatus(ApplicationStatus.PASSIVE);
        return true;
    }
}
