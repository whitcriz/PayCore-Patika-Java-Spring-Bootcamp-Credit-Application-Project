package com.paycore.patika.credit_application_system.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static javax.persistence.FetchType.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
@Entity
@Table(name = "credit_application")
public class CreditApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id", updatable = false, nullable = false)
    private Integer applicationId;

    @CreationTimestamp
    @JsonFormat( pattern = "dd-MM-yyyy" )
    @Column(name="credit_application_date", updatable = false, nullable = false)
    private LocalDate creditApplicationDate;

    @Transient
    private final Integer creditMultiplier = 4;

    @Enumerated(EnumType.STRING)
    @Column(name = "resulted")
    private Resulted resulted ;

    @Enumerated(EnumType.STRING)
    @Column(name = "credit_result")
    private CreditResult creditResult ;

    @Column(name = "credit_limit")
    private Double creditLimit;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    @NotNull(message = "Customer can not be null for credit application")
    @JoinColumn(name = "national_identity_number", referencedColumnName = "national_identity_number")
    @ManyToOne(fetch = LAZY)
    private Customer customer;

    public CreditApplication(Customer customer) {
        this.customer = customer;
    }
}
