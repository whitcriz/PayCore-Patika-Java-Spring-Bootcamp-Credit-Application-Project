package com.paycore.patika.credit_application_system.service.impl;

import com.paycore.patika.credit_application_system.exception.InvalidCreditApplyException;
import com.paycore.patika.credit_application_system.exception.NotFoundException;
import com.paycore.patika.credit_application_system.messaging.producer.CustomerProducer;
import com.paycore.patika.credit_application_system.model.entity.CreditLimit;
import com.paycore.patika.credit_application_system.model.entity.CreditResult;
import com.paycore.patika.credit_application_system.model.entity.*;
import com.paycore.patika.credit_application_system.repository.CreditApplicationRepository;
import com.paycore.patika.credit_application_system.service.CreditApplicationService;
import com.paycore.patika.credit_application_system.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreditApplicationServiceImpl implements CreditApplicationService {

    private final CreditApplicationRepository creditApplicationRepository;
    private final NotificationService notificationService;


    @Override
    public boolean createCreditApplication(Customer customer) {
        CreditApplication creditApplication = new CreditApplication(customer);
        creditApplication.setCreditResult(CreditResult.NOT_RESULTED);
        creditApplication.setApplicationStatus(ApplicationStatus.ACTIVE);
        creditApplicationRepository.save(creditApplication);

        return true;
    }


    private CreditApplication getActiveCreditApplicationByCustomer(Customer customer) {

       return creditApplicationRepository.findAll().stream()
                .filter(creditApplication -> creditApplication.getCustomer() == customer)
                .filter(creditApplication -> creditApplication.getApplicationStatus() == ApplicationStatus.ACTIVE)
                .findAny()
                        .orElseThrow(() -> new NotFoundException("Active Credit Application"));
    }

    @Override
    public CreditApplication getActiveAndApprovedCreditApplicationByCustomer(Customer customer) {

        if(getActiveCreditApplicationByCustomer(customer).getCreditResult().equals(CreditResult.NOT_RESULTED)) {
            throw new InvalidCreditApplyException("and have not resulted yet, you will be informed via phone message when resulted.");
        }

        return getActiveCreditApplicationByCustomer(customer);
    }

    @Override
    public boolean isThereAnyActiveAndApprovedApplicationByCustomer(Customer customer) {
        return creditApplicationRepository.findAll().stream()
                .anyMatch(c -> c.getCustomer().equals(customer)
                        && c.getApplicationStatus().equals(ApplicationStatus.ACTIVE)
                        && c.getCreditResult().equals(CreditResult.APPROVED));
    }

    private CreditApplication getNotResultedCreditApplicationByCustomer(String nationalIdentityNumber) {
        return creditApplicationRepository.findAll().stream()
                .filter(c -> c.getCustomer().getNationalIdentityNumber().matches(nationalIdentityNumber) && c.getCreditResult().equals(CreditResult.NOT_RESULTED))
                .findAny()
                .orElseThrow(()-> new NotFoundException("Not Resulted Credit Application"));
    }


    @Override
    public void UpdateNotResultedApplication(Integer score, String nationalIdentityNumber) {

        CreditApplication applicationResulting = getNotResultedCreditApplicationByCustomer(nationalIdentityNumber);

        applicationResulting.getCustomer().setCreditScore(score);
        Double income = applicationResulting.getCustomer().getMonthlyIncome();
        Integer creditMultiplier = applicationResulting.getCreditMultiplier();

        boolean creditLimitCondition = (income >= CreditLimit.HIGHER.getIncomeLimit());
        boolean creditScoreConditionForApproval = (score >= CreditResult.REJECTED.getCreditScoreLimit());
        boolean creditScoreConditionForSpecialLimit = (score >= CreditResult.APPROVED.getCreditScoreLimit());

        if(creditScoreConditionForApproval) {

            applicationResulting.setCreditResult(CreditResult.APPROVED);

            if (creditScoreConditionForSpecialLimit) {
                CreditLimit.SPECIAL.setCreditLimitAmount(income * creditMultiplier);
                applicationResulting.setCreditLimit(CreditLimit.SPECIAL.getCreditLimitAmount());

            }else if(creditLimitCondition){

                 applicationResulting.setCreditLimit(CreditLimit.HIGHER.getCreditLimitAmount());

            }else{

                applicationResulting.setCreditLimit(CreditLimit.LOWER.getCreditLimitAmount());
            }

        } else {
            applicationResulting.setCreditResult(CreditResult.REJECTED);
            applicationResulting.setApplicationStatus(ApplicationStatus.PASSIVE);
        }

        creditApplicationRepository.save(applicationResulting);
        System.out.println(notificationService.sendMessageForResult(applicationResulting));
    }


    @Override
    public boolean deleteCreditApplication(Customer customer) {
        creditApplicationRepository.delete(getActiveAndApprovedCreditApplicationByCustomer(customer));
        return true;
    }

}