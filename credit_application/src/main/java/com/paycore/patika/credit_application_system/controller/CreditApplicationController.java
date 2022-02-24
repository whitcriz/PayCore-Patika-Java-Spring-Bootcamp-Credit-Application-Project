package com.paycore.patika.credit_application_system.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paycore.patika.credit_application_system.messaging.producer.CustomerProducer;
import com.paycore.patika.credit_application_system.model.CreditApplicationResultedDTO;
import com.paycore.patika.credit_application_system.model.CreditApplyDTO;
import com.paycore.patika.credit_application_system.model.entity.CreditApplication;
import com.paycore.patika.credit_application_system.model.mapper.CreditApplicationResultedMapper;
import com.paycore.patika.credit_application_system.model.mapper.CreditApplyMapper;
import com.paycore.patika.credit_application_system.service.CreditApplicationService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class CreditApplicationController {

    private final CreditApplicationService creditApplicationService;
    private final CustomerProducer customerProducer;

    private static final CreditApplicationResultedMapper CREDIT_APPLICATION_MAPPER = Mappers.getMapper(CreditApplicationResultedMapper.class);

    @GetMapping("/all")
    public List<CreditApplicationResultedDTO> getAllCreditApplications() {
        List<CreditApplication> allCreditApplications = creditApplicationService.getAllCreditApplications();
        return allCreditApplications.stream().map(CREDIT_APPLICATION_MAPPER::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/{customerNationalIdentityNumber}")
    public CreditApplicationResultedDTO getCreditApplicationByCustomer(@PathVariable @Size(min = 11, max = 11) @JsonFormat(pattern = "[1-9][0-9]{10}") String customerNationalIdentityNumber) {
        CreditApplication creditApplication = creditApplicationService.getCreditApplicationByCustomer(customerNationalIdentityNumber);
        return CREDIT_APPLICATION_MAPPER.toDto(creditApplication);
    }

    @PostMapping("/create")
    public boolean createCreditApplication(@Valid @RequestBody CreditApplyDTO creditApplyingDTO) {
        String customerNationalIdentityNumber = creditApplyingDTO.getCustomer().getNationalIdentityNumber();
        customerProducer.publishCustomer(customerNationalIdentityNumber);
        return creditApplicationService.createCreditApplication(CreditApplyMapper.toEntity(creditApplyingDTO));
    }

    /*@DeleteMapping(value = "/delete")
    public boolean deleteCreditApplication(@RequestParam @Min(1) Integer id) {
        return creditApplicationService.deleteCreditApplication(id);
    }*/
}
