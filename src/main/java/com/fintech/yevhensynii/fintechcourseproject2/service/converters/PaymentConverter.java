package com.fintech.yevhensynii.fintechcourseproject2.service.converters;

import com.fintech.yevhensynii.fintechcourseproject2.model.dto.PaymentDto;
import com.fintech.yevhensynii.fintechcourseproject2.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentConverter {
    public Payment fromPaymentDtoToPayment(PaymentDto paymentDto){
        Payment payment = new Payment();
        payment.setPaymentId(paymentDto.getPaymentId());
        payment.setCardNum(paymentDto.getCardNum());
        payment.setSum(paymentDto.getSum());
        payment.setStatus(paymentDto.getStatus());
        payment.setDateCreate(paymentDto.getDateCreate());
        payment.setDateStatus(paymentDto.getDateStatus());
        payment.setSampleId(paymentDto.getSampleId());
        return payment;
    }

    public PaymentDto fromPaymentToPaymentDto(Payment payment){
        return PaymentDto.builder()
                .paymentId(payment.getPaymentId())
                .cardNum(payment.getCardNum())
                .sum(payment.getSum())
                .status(payment.getStatus())
                .dateCreate(payment.getDateCreate())
                .dateStatus(payment.getDateStatus())
                .sampleId(payment.getSampleId())
                .build();
    }
}
