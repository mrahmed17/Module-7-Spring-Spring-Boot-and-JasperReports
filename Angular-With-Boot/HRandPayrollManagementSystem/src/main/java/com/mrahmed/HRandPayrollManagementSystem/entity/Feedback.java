package com.mrahmed.HRandPayrollManagementSystem.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "feedbacks")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PRIVATE) // Make the no-argument constructor private
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String rating;
    private String comment;
    private Date date; //java.time.LocalDateTime

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user; //Id, employeeName


//    @Version
//    @NotNull
//    private Long version;

//    @PrePersist
//    public void validateRating() {
//        if (rating == null || rating.isEmpty() || rating.length() > 1) {
//            throw new RuntimeException("Invalid rating");
//        }
//        int ratingValue = Integer.parseInt(rating);
//        if (ratingValue < 1 || ratingValue > 5) {
//            throw new RuntimeException("Rating must be between 1 and 5");
//        }
//    }

}
