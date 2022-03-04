package com.paycore.patika.credit_application_system.controller;

import com.paycore.patika.credit_application_system.exception.InvalidCreditApplyException;
import com.paycore.patika.credit_application_system.messaging.producer.CustomerProducer;
import com.paycore.patika.credit_application_system.model.CreditApplicationResultedDTO;
import com.paycore.patika.credit_application_system.model.entity.*;
import com.paycore.patika.credit_application_system.model.mapper.CreditApplicationResultedMapper;
import com.paycore.patika.credit_application_system.repository.CreditApplicationRepository;
import com.paycore.patika.credit_application_system.service.CreditApplicationService;
import com.paycore.patika.credit_application_system.service.CustomerService;
import com.paycore.patika.credit_application_system.service.ObtainCreditService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class CreditApplicationController {

    private final CreditApplicationService creditApplicationService;
    private final CustomerService customerService;
    private final ObtainCreditService obtainCreditService;

    private static final CreditApplicationResultedMapper CREDIT_APPLICATION_MAPPER = Mappers.getMapper(CreditApplicationResultedMapper.class);


    @GetMapping(value = "/{nationalIdentityNumber}")
    public CreditApplicationResultedDTO getActiveAndApprovedCreditApplicationByCustomer(@PathVariable @Pattern(regexp = "[1-9][0-9]{10}") String nationalIdentityNumber) {
        Customer customer = customerService.getCustomer(nationalIdentityNumber);
        return CREDIT_APPLICATION_MAPPER.toDto(creditApplicationService.getActiveAndApprovedCreditApplicationByCustomer(customer));
    }

    @PostMapping(value = "/create/{nationalIdentityNumber}")
    public String createCreditApplication(@PathVariable @Pattern(regexp = "[1-9][0-9]{10}") String nationalIdentityNumber) {
        Customer applyCustomer = customerService.getCustomer(nationalIdentityNumber);
        if(creditApplicationService.isThereAnyActiveApplicationByCustomer(applyCustomer)) {
            throw new InvalidCreditApplyException("and have not resulted yet, you will be informed via phone message when resulted.");
        }
        return creditApplicationService.createCreditApplication(applyCustomer);
    }

    @PutMapping(value = "/get-credit/{nationalIdentityNumber}")
    public String obtainCreditByCreditApplication(@PathVariable @Pattern(regexp = "[1-9][0-9]{10}")String nationalIdentityNumber) {
        return obtainCreditService.obtainCreditByCreditApplication(
                CREDIT_APPLICATION_MAPPER.toEntity(
                        getActiveAndApprovedCreditApplicationByCustomer(nationalIdentityNumber)
                )
        );
    }

    @DeleteMapping(value = "/delete/{nationalIdentityNumber}")
    public boolean deleteCreditApplication(@PathVariable @Pattern(regexp = "[1-9][0-9]{10}") String nationalIdentityNumber) {
        Customer customer = customerService.getCustomer(nationalIdentityNumber);
        return creditApplicationService.deleteCreditApplication(customer);
    }

}
