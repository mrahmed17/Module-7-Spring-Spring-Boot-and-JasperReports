package com.mrahmed.HRandPayrollManagementSystem.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "attendances")
@Data
@AllArgsConstructor
@NoArgsConstructor
// @NoArgsConstructor(access = AccessLevel.PRIVATE) // Make the no-argument constructor private
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date; //java.time.LocalDate
    private Date clockInTime; //java.time.LocalTime
    private Date clockOutTime; //java.time.LocalTime

//    @Version
//    private Long version;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

//    @PrePersist
//    public void validateClockTimes() {
//        if (clockOutTime.isBefore(clockInTime)) {
//            throw new RuntimeException("Clock out time cannot be earlier than clock in time");
//        }
//    }


}
