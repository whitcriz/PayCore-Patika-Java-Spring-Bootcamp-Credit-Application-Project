package com.paycore.patika.credit_application_system.service.impl;

import com.paycore.patika.credit_application_system.model.entity.CreditApplication;
import com.paycore.patika.credit_application_system.model.entity.CreditResult;
import com.paycore.patika.credit_application_system.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public String sendMessageForResult(CreditApplication creditApplication) {
        String resultMessage = "Your credit application has " + creditApplication.getCreditResult() ;
        if(creditApplication.getCreditResult() == CreditResult.APPROVED) {
            resultMessage += " and your credit limit is " + creditApplication.getCreditLimit() + " TL.";
        }

        return "Notification message is sent to " + creditApplication.getCustomer().getPhone() + " number with the message : " + resultMessage ;
    }
}
