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
    private final CustomerProducer customerProducer;

    @Override
    public String createCreditApplication(Customer customer) {
        CreditApplication creditApplication = new CreditApplication(CreditResult.NOT_RESULTED,null, ApplicationStatus.ACTIVE, customer);
        creditApplicationRepository.save(creditApplication);
        return customerProducer.publishCustomer(customer);
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
    public boolean isThereAnyActiveApplicationByCustomer(Customer customer) {
        if(isThereAnyActiveAndApprovedApplicationByCustomer(customer)) {
            throw new InvalidCreditApplyException(", you can not apply again before obtaining credit or deleting the application!");
        }
        return creditApplicationRepository.findAll().stream()
                .anyMatch(c -> c.getCustomer().equals(customer)
                        && c.getApplicationStatus().equals(ApplicationStatus.ACTIVE));
    }

    private boolean isThereAnyActiveAndApprovedApplicationByCustomer(Customer customer) {
         return creditApplicationRepository.findAll().stream()
                 .anyMatch(c -> c.getCustomer().equals(customer)
                             && c.getApplicationStatus().equals(ApplicationStatus.ACTIVE)
                             && c.getCreditResult().equals(CreditResult.APPROVED));
    }

    private CreditApplication getNotResultedCreditApplicationByCustomer(Customer customer) {
        return customer.getCreditApplications().stream()
                .filter(c -> c.getCreditResult().equals(CreditResult.NOT_RESULTED))
                .findAny()
                .get();
    }

    private void creditLimitCalculator(CreditApplication application) {

        Customer creditCustomer = application.getCustomer();
        Integer score = creditCustomer.getCreditScore();
        Double income = creditCustomer.getMonthlyIncome();
        Integer creditMultiplier = application.getCreditMultiplier();

        boolean creditLimitCondition = (income >= CreditLimit.HIGHER.getIncomeLimit());
        boolean creditScoreConditionForSpecialLimit = (score >= CreditResult.APPROVED.getCreditScoreLimit());

        if (creditScoreConditionForSpecialLimit) {
            CreditLimit.SPECIAL.setCreditLimitAmount(income * creditMultiplier);
            application.setCreditLimit(CreditLimit.SPECIAL.getCreditLimitAmount());

        }else if(creditLimitCondition){

            application.setCreditLimit(CreditLimit.HIGHER.getCreditLimitAmount());

        }else{

            application.setCreditLimit(CreditLimit.LOWER.getCreditLimitAmount());
        }
    }

    @Override
    public void UpdateNotResultedApplication(Customer creditCustomer) {
        CreditApplication applicationResulting = getNotResultedCreditApplicationByCustomer(creditCustomer);
        System.out.println("getting credit application for resulting");

        Integer score = creditCustomer.getCreditScore();
        boolean creditScoreConditionForApproval = (score >= CreditResult.REJECTED.getCreditScoreLimit());

        if(creditScoreConditionForApproval) {

            applicationResulting.setCreditResult(CreditResult.APPROVED);
            creditLimitCalculator(applicationResulting);

        } else {
            applicationResulting.setCreditResult(CreditResult.REJECTED);
            applicationResulting.setApplicationStatus(ApplicationStatus.PASSIVE);
        }

        creditApplicationRepository.save(applicationResulting);
        System.out.println("resulted the application");
        System.out.println(notificationService.sendMessageForResult(applicationResulting));
    }


    @Override
    public boolean deleteCreditApplication(Customer customer) {
        creditApplicationRepository.delete(getActiveAndApprovedCreditApplicationByCustomer(customer));
        return true;
    }

}