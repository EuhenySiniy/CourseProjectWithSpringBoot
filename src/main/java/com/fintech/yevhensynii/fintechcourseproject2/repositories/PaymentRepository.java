package com.fintech.yevhensynii.fintechcourseproject2.repositories;

import com.fintech.yevhensynii.fintechcourseproject2.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Transactional
    @Modifying
    @Query(value = "update payments set status=?, date_status=now() where payment_id=?", nativeQuery = true)
    void saveNewStatusPayment(String newStatus, Long paymentId);
}
