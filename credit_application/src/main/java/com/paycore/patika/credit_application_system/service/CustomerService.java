package com.paycore.patika.credit_application_system.service;

import com.paycore.patika.credit_application_system.model.entity.Customer;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

public interface CustomerService {

    boolean addCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomer(String nationalIdentityNumber);

    Customer updateCustomer(@RequestBody Customer customer);

    Customer updateCustomerCreditScore(Customer customer);

    boolean deleteCustomer(String nationalIdentityNumber);
}
