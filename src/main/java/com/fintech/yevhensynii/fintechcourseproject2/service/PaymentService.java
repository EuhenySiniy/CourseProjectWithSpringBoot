package com.fintech.yevhensynii.fintechcourseproject2.service;

import com.fintech.yevhensynii.fintechcourseproject2.model.dto.PaymentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PaymentService {
    String createNewPayment(PaymentDto paymentDto);

    List<PaymentDto> showPaymentsWithStatusNew();

    void updateStatusPayments(Map<Long, String> updatedPayments);

    List<PaymentDto> showAllPayments();
}
