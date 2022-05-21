package com.fintech.yevhensynii.fintechcourseproject2.service.implementation;

import com.fintech.yevhensynii.fintechcourseproject2.model.dto.PaymentDto;
import com.fintech.yevhensynii.fintechcourseproject2.service.PaymentProcessingService;
import com.fintech.yevhensynii.fintechcourseproject2.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log4j2
public class PaymentProcessingServiceImpl implements PaymentProcessingService {
    private final PaymentService paymentService;
    private static final String SUCCESS_PAYMENT = "Paid";
    private static final String FAILED_PAYMENT = "Rejected";

    @Override
    @Scheduled(fixedDelayString = "PT1S")
    public void checkNewPayment() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Instant currentTime = timestamp.toInstant();
        List<PaymentDto> afterTwoSeconds = paymentService.showPaymentsWithStatusNew()
                .stream()
                .filter(paymentDto -> paymentDto.getDateCreate().toInstant().plusSeconds(2)
                        .isBefore(currentTime))
                .toList();
        if(afterTwoSeconds.size()>0) setNewPaymentStatus(afterTwoSeconds);
    }

    @Override
    public void setNewPaymentStatus(List<PaymentDto> payments) {
        Random random = new Random();
        Map<Long, String> updatedPayments = payments.stream()
                .peek(paymentDto -> {
                    int res = random.nextInt(3);
                    if(res == 1) paymentDto.setStatus(SUCCESS_PAYMENT);
                    else if(res == 2) paymentDto.setStatus(FAILED_PAYMENT);
                }).filter(paymentDto -> paymentDto.getStatus().equals("Paid") || paymentDto.getStatus().equals("Rejected"))
                .collect(Collectors.toMap(PaymentDto::getPaymentId, PaymentDto::getStatus));
        System.out.println(updatedPayments);
        paymentService.updateStatusPayments(updatedPayments);
    }
}
