package com.mrahmed.HRandPayrollManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "leaves")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate requestDate;
    private boolean isApproved;
    private int remainingLeave; // total 25 days: (reserve 10 + sick 15 days), remainingcalculation(total -(end-startdate))


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}

