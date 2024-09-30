package com.mrahmed.HRandPayrollManagementSystem.repository;

import com.mrahmed.HRandPayrollManagementSystem.entity.PaymentReceipt;
import com.mrahmed.HRandPayrollManagementSystem.entity.User;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentReceiptRepository extends JpaRepository <PaymentReceipt, Long> {

    List<PaymentReceipt> findByPaymentReceiver(User paymentReceiver);

    @Query("SELECT pr FROM PaymentReceipt pr WHERE pr.paymentDate BETWEEN :startDate AND :endDate")
    List<PaymentReceipt> findByPaymentDateBetween(@Param("startDate") LocalDateTime startDate,
                                                  @Param("endDate") LocalDateTime endDate);

    @Query("SELECT SUM(pr.netPaidAmount) FROM PaymentReceipt pr WHERE YEAR(pr.paymentDate) = :year")
    Double findTotalAmountByYear(@Param("year") int year);

    @Query("SELECT SUM(pr.netPaidAmount) FROM PaymentReceipt pr WHERE MONTH(pr.paymentDate) = :month")
    Double findTotalAmountByMonth(@Param("month") int month);

    @Query("SELECT pr FROM PaymentReceipt pr WHERE pr.paymentReceiver = :paymentReceiver ORDER BY pr.paymentDate DESC")
    Optional<PaymentReceipt> findLatestReceiptByPaymentReceiver(@Param("paymentReceiver") User paymentReceiver);

    @Query("SELECT pr FROM PaymentReceipt pr WHERE pr.paymentReceiver.fullName LIKE %:name%")
    List<PaymentReceipt> findByUserFullName(@Param("name") String name);

    @Query("SELECT pr FROM PaymentReceipt pr WHERE pr.status = :status")
    List<PaymentReceipt> findByPaymentStatus(@Param("status") String status);

    @Query("SELECT SUM(pr.netPaidAmount) FROM PaymentReceipt pr WHERE pr.paymentReceiver.id = :userId")
    Double findTotalPaymentsByUserId(@Param("userId") Long userId);


}
