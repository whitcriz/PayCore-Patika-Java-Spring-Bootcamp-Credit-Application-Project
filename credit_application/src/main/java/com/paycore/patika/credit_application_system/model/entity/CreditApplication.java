package com.paycore.patika.credit_application_system.model.entity;

import com.paycore.patika.credit_application_system.model.CreditLimit;
import com.paycore.patika.credit_application_system.model.CreditResult;
import com.paycore.patika.credit_application_system.model.Resulted;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.FetchType.*;


// import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "credit_application")
public class CreditApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @CreationTimestamp
    @Column(name="credit_application_date", updatable = false, nullable = false)
    private LocalDate creditApplicationDate;

    @Transient
    private final Integer creditMultiplier = 4;

    @Enumerated
    @Column(name = "resulted")
    private Resulted resulted;

    @Enumerated(EnumType.STRING)
    @Column(name = "credit_result")
    private CreditResult creditResult;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "credit_limit")
    private CreditLimit creditLimit;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

}
