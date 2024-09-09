package com.mrahmed.HRandPayrollManagementSystem.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "bonuses")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PRIVATE) // Make the no-argument constructor private
public class Bonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private BigDecimal bonusAmount;
    private Date bonusDate; //java.time.LocalDate

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;


//    @Version
//    @NotNull
//    private Long version;

//    @PrePersist
//    public void validateBonusAmount() {
//        if (bonusAmount.compareTo(BigDecimal.ZERO) < 0) {
//            throw new RuntimeException("Bonus amount cannot be negative");
//        }
//    }

}
