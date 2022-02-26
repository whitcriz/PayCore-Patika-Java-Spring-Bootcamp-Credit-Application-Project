package com.paycore.patika.credit_application_system.service.impl;

import com.paycore.patika.credit_application_system.exception.InvalidCreditApplyException;
import com.paycore.patika.credit_application_system.exception.NotFoundException;
import com.paycore.patika.credit_application_system.messaging.producer.CreditApplicationProducer;
import com.paycore.patika.credit_application_system.model.entity.CreditLimit;
import com.paycore.patika.credit_application_system.model.entity.CreditResult;
import com.paycore.patika.credit_application_system.model.entity.Resulted;
import com.paycore.patika.credit_application_system.model.entity.*;
import com.paycore.patika.credit_application_system.repository.CreditApplicationRepository;
import com.paycore.patika.credit_application_system.service.CreditApplicationService;
import com.paycore.patika.credit_application_system.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CreditApplicationServiceImpl implements CreditApplicationService {

    private final CreditApplicationRepository creditApplicationRepository;
    private final CreditApplicationProducer creditApplicationProducer;
    private final NotificationService notificationService;


    @Override
    public List<CreditApplication> getAllCreditApplicationsByCustomer(Customer customer) {
        if(customer.getCreditApplications().isEmpty()){
           throw  new NotFoundException("Any Credit Applications");
       }
       return customer.getCreditApplications();
    }

    @Override
    public String createCreditApplication(Customer customer) {
        if (getAllCreditApplicationsByCustomer(customer).stream().anyMatch(creditApplication -> creditApplication.getResulted()==Resulted.NO)) {
            throw new InvalidCreditApplyException("and could not resulted yet, you will be notified with SMS when resulted.");
        }
        CreditApplication creditApplication = new CreditApplication(customer);
        creditApplication.setResulted(Resulted.NO);
        creditApplication.setApplicationStatus(ApplicationStatus.ACTIVE);
        creditApplicationRepository.save(creditApplication);
        return creditApplicationProducer.publishCustomer(customer);
    }

    @Override
    public CreditApplication getActiveCreditApplicationByCustomer(Customer customer) {
        List<CreditApplication> allCreditApplicationsByCustomer = getAllCreditApplicationsByCustomer(customer);
        Optional<CreditApplication> apply = allCreditApplicationsByCustomer.stream().findAny()
                .filter(creditApplication -> creditApplication.getApplicationStatus() == ApplicationStatus.ACTIVE)
                .filter(creditApplication -> creditApplication.getResulted() == Resulted.YES);
        return apply.orElseThrow(() -> new NotFoundException("Resulted Active Credit Application"));
    }

    @Override
    public void UpdateNotResultedApplication(Customer customer) {
        CreditApplication applicationNotResulted = getNotResultedCreditApplicationByCustomer(customer);
        Integer score = customer.getCreditScore();
        Double income = customer.getMonthlyIncome();
        Integer creditMultiplier = applicationNotResulted.getCreditMultiplier();
        boolean creditLimitCondition = income >= CreditLimit.HIGHER.getIncomeLimit();
        boolean creditScoreConditionForApproval = score >= CreditResult.REJECTED.getCreditScoreLimit();
        boolean creditScoreConditionForSpecialLimit = score >= CreditResult.APPROVED.getCreditScoreLimit();

        if(creditScoreConditionForApproval) {
            applicationNotResulted.setCreditResult(CreditResult.APPROVED);
            if (creditScoreConditionForSpecialLimit) {
                CreditLimit.SPECIAL.setCreditLimitAmount(income * creditMultiplier);
                applicationNotResulted.setCreditLimit(CreditLimit.SPECIAL.getCreditLimitAmount());
            } else {
                if (creditLimitCondition) {
                    applicationNotResulted.setCreditLimit(CreditLimit.HIGHER.getCreditLimitAmount());
                } else {
                    applicationNotResulted.setCreditLimit(CreditLimit.LOWER.getCreditLimitAmount());
                }
            }
        } else {
            applicationNotResulted.setCreditResult(CreditResult.REJECTED);
            applicationNotResulted.setApplicationStatus(ApplicationStatus.PASSIVE);
        }

        applicationNotResulted.setResulted(Resulted.YES);
        creditApplicationRepository.save(applicationNotResulted);
        System.out.println(notificationService.sendMessageForResult(applicationNotResulted));
    }


    @Override
    public boolean deleteCreditApplication(Customer customer) {
        creditApplicationRepository.delete(getActiveCreditApplicationByCustomer(customer));
        return true;
    }

    private CreditApplication getNotResultedCreditApplicationByCustomer(Customer customer) {
        List<CreditApplication> allApplications = getAllCreditApplicationsByCustomer(customer);
        return allApplications.stream().findAny()
                .filter(c -> c.getResulted().equals(Resulted.NO)).orElseThrow(()-> new NotFoundException("Not Resulted Credit Application"));
    }

}