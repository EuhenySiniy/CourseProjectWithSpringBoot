package com.fintech.yevhensynii.fintechcourseproject2.service;

import com.fintech.yevhensynii.fintechcourseproject2.model.dto.PaymentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentProcessingService {
    void checkNewPayment();

    void setNewPaymentStatus(List<PaymentDto> payments);
}
