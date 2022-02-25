package com.paycore.patika.credit_score_service.service;

import com.paycore.patika.credit_score_service.model.entity.Customer;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CustomerService {

    Customer addCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomer(String nationalIdentityNumber);

    Customer updateCustomer(@RequestBody Customer customer);

    boolean deleteCustomer(Integer id);

    boolean deleteCustomer(String nationalIdentityNumber);
}
