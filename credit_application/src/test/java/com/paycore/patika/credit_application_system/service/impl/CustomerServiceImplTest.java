package com.paycore.patika.credit_application_system.service.impl;

import com.paycore.patika.credit_application_system.model.entity.ApplicationStatus;
import com.paycore.patika.credit_application_system.model.entity.CreditApplication;
import com.paycore.patika.credit_application_system.model.entity.CreditResult;
import com.paycore.patika.credit_application_system.model.entity.Customer;
import com.paycore.patika.credit_application_system.repository.CustomerRepository;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    /*@BeforeEach
    void setUp() {

    }
*/
    @Test
    public void whenAddCustomerCalledWithValidCustomer_itShouldReturnTrue() {

        //Customer testCustomer = new Customer("16419939255","Fran","Camel",
          //      15327.69,"MALE",41,"05586137967","camel7937@outlook.edu",
            //    55, new ArrayList<>());

       // CreditResult creditResult = CreditResult.REJECTED;
        //ApplicationStatus applicationStatus = ApplicationStatus.PASSIVE;

       //CreditApplication creditApplication1 = new CreditApplication(1, LocalDate.of(2022,2,16),
         //       creditResult,0.00, applicationStatus, testCustomer);


        //List<CreditApplication> creditApplications = Arrays.asList(creditApplication1);
        Customer updatedTestCustomer = new Customer("16419939255","Fran","Camel",
               15327.69,"MALE",41,"05586137967","camel7937@outlook.edu",
              20, null);


        when(customerRepository.save(updatedTestCustomer)).thenReturn(updatedTestCustomer);

        assertTrue(customerService.addCustomer(updatedTestCustomer));

        verify(customerRepository, times(1)).save(updatedTestCustomer);

    }


    @Test
    public void getAllCustomers() {
    }

    @Test
    public void getCustomer() {
    }

    @Test
    public void updateCustomer() {
    }

    @Test
    public void deleteCustomer() {
    }
}