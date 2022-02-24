package com.paycore.patika.credit_application_system.service.impl;

import com.paycore.patika.credit_application_system.exception.InvalidRequestException;
import com.paycore.patika.credit_application_system.model.CreditLimit;
import com.paycore.patika.credit_application_system.model.CreditResult;
import com.paycore.patika.credit_application_system.model.Resulted;
import com.paycore.patika.credit_application_system.model.entity.*;
import com.paycore.patika.credit_application_system.repository.CreditApplicationRepository;
import com.paycore.patika.credit_application_system.repository.CustomerRepository;
import com.paycore.patika.credit_application_system.service.CreditApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CreditApplicationServiceImpl implements CreditApplicationService {

    private final CreditApplicationRepository creditApplicationRepository;
    private final CustomerRepository customerRepository;


    @Override
    public boolean createCreditApplication(CreditApplication creditApplication) {
        creditApplication.setCreditApplicationDate(LocalDate.now());
        creditApplication.setResulted(Resulted.NO);
        creditApplicationRepository.save(creditApplication);
        return true;
    }

    @Override
    public List<CreditApplication> getAllCreditApplications() {
        return creditApplicationRepository.findAll();
    }

    @Override
    public CreditApplication getCreditApplicationByCustomer(String customerNationalIdentityNumber) {
        Optional<Customer> customer = customerRepository.findByNationalIdentityNumber(customerNationalIdentityNumber);
        return customer.orElseThrow(() -> new InvalidRequestException("There is no customer here!")).getCreditApplication();
    }

    @Override
    public CreditApplication updateCreditApplication(@Valid CreditApplication creditApplication) {

        if(creditApplication.getId() == null) {
            throw new InvalidRequestException("Credit application can not be null for update!");
        }
        Customer customer = creditApplication.getCustomer();
        Integer score = customer.getCreditScore();
        Double income = customer.getMonthlyIncome();
        Integer creditMultiplier = creditApplication.getCreditMultiplier();

        if (score < CreditResult.REJECT.getCreditScoreLimit()) {
            creditApplication.setCreditResult(CreditResult.REJECT);
        }else{

            creditApplication.setCreditResult(CreditResult.APPROVE);
            if(score < CreditResult.APPROVE.getCreditScoreLimit()){

                if(income < CreditResult.APPROVE.getIncomeLimit()){
                    creditApplication.setCreditLimit(CreditLimit.LOWER);
                }else {
                    creditApplication.setCreditLimit(CreditLimit.HIGHER);
                }

            }else{
                CreditLimit.SPECIAL.setCreditLimitAmount(income * creditMultiplier);
                creditApplication.setCreditLimit(CreditLimit.SPECIAL);
            }
        }

        creditApplication.setResulted(Resulted.YES);
        return creditApplicationRepository.save(creditApplication);
    }

}