package com.fintech.yevhensynii.fintechcourseproject2.service.implementation;

import com.fintech.yevhensynii.fintechcourseproject2.model.dto.PaymentDto;
import com.fintech.yevhensynii.fintechcourseproject2.model.Payment;
import com.fintech.yevhensynii.fintechcourseproject2.repositories.PaymentRepository;
import com.fintech.yevhensynii.fintechcourseproject2.service.PaymentService;
import com.fintech.yevhensynii.fintechcourseproject2.service.converters.PaymentConverter;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log4j2
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentConverter paymentConverter;

    @Override
    public String createNewPayment(PaymentDto paymentDto) {
        if(paymentDto.getSum()==null) {
            log.warn("Unable to make a payment without the amount");
            throw new ValidationException("Unable to make a payment without the amount!");
        }
        paymentDto.setStatus("New");
        paymentDto.setDateCreate(Timestamp.from(new Timestamp(System.currentTimeMillis()).toInstant()));
        paymentDto.setDateStatus(Timestamp.from(new Timestamp(System.currentTimeMillis()).toInstant()));
        Payment savedPayment = paymentRepository.save(paymentConverter.fromPaymentDtoToPayment(paymentDto));
        log.info(savedPayment.getPaymentId() + " payment was created");
        return "Payment was created";
    }

    @Override
    public List<PaymentDto> showPaymentsWithStatusNew() {
        return paymentRepository.findAll()
                .stream()
                .filter(payment -> payment.getStatus().equals("New"))
                .map(paymentConverter::fromPaymentToPaymentDto)
                .toList();
    }

    @Override
    public void updateStatusPayments(Map<Long, String> updatedPayments) {
        updatedPayments.forEach((k, v) -> paymentRepository.saveNewStatusPayment(v, k));
    }

    @Override
    public List<PaymentDto> showAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(paymentConverter::fromPaymentToPaymentDto)
                .collect(Collectors.toList());
    }


}
