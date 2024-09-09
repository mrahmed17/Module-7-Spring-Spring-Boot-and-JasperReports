package com.mrahmed.HRandPayrollManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "leaves")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PRIVATE) // Make the no-argument constructor private
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String leaveReason; // Reserve or Sick
    private Date startDate; //java.time.LocalDate
    private Date endDate; //java.time.LocalDate
    private double remainingLeave; // Total 25 days: (Reserve 10 + sick 15), remaining: (total - (end - start))
    private Date requestDate; //java.time.LocalDate
    private boolean isApproved;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private User user; // id, name, contact


//    @Version
//    @NotNull
//    private Long version;

//    @PrePersist
//    public void validateRemainingLeave() {
//        if (remainingLeave < 0) {
//            throw new RuntimeException("Remaining leave cannot be negative");
//        }
//    }

//    @PrePersist
//    public void validateDates() {
//        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
//            throw new RuntimeException("Start date cannot be after end date");
//        }
//    }

}
