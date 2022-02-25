package com.paycore.patika.credit_score_service.model;

import com.paycore.patika.credit_score_service.model.entity.Customer;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreditApplication implements Serializable {
    private Integer applicationId;
    private Customer customer;
    private Integer creditScore;
}
