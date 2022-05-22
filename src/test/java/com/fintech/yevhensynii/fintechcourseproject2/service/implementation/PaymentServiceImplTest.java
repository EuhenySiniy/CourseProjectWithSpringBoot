package com.fintech.yevhensynii.fintechcourseproject2.service.implementation;

import com.fintech.yevhensynii.fintechcourseproject2.model.dto.PaymentDto;
import com.fintech.yevhensynii.fintechcourseproject2.repositories.PaymentRepository;
import com.fintech.yevhensynii.fintechcourseproject2.service.PaymentService;
import com.fintech.yevhensynii.fintechcourseproject2.service.converters.PaymentConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.validation.ValidationException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceImplTest {
    @Autowired
    private PaymentService paymentService;

    @MockBean
    private PaymentRepository paymentRepository;

    @MockBean
    private PaymentConverter paymentConverter;

    @Test
    void createNewPaymentSuccessfully() {
        PaymentDto payment = new PaymentDto(
                1L,
                "4441114412341234",
                new BigDecimal("1.0"),
                null,
                null,
                null,
                1L
        );
        paymentService.createNewPayment(payment);

        Assertions.assertTrue(true, "Payment was created");
        Assertions.assertNotNull(payment.getStatus());
        Assertions.assertNotNull(payment.getDateCreate());
        Assertions.assertNotNull(payment.getDateStatus());

        Mockito.verify(paymentConverter, Mockito.times(1)).fromPaymentDtoToPayment(payment);
        Mockito.verify(paymentRepository, Mockito.times(1))
                .save(paymentConverter.fromPaymentDtoToPayment(payment));
    }

    @Test
    void createNewPaymentWithoutCard() {
        PaymentDto payment = new PaymentDto(
                1L,
                null,
                new BigDecimal("1.0"),
                null,
                null,
                null,
                1L
        );
        Assertions.assertThrows(ValidationException.class,
                () -> paymentService.createNewPayment(payment));

        Mockito.verify(paymentConverter, Mockito.times(0)).fromPaymentDtoToPayment(payment);
        Mockito.verify(paymentRepository, Mockito.times(0))
                .save(paymentConverter.fromPaymentDtoToPayment(payment));
    }

    @Test
    void createNewPaymentWithoutSum() {
        PaymentDto payment = new PaymentDto(
                1L,
                "4441114412341234",
                null,
                null,
                null,
                null,
                1L
        );
        Assertions.assertThrows(ValidationException.class,
                () -> paymentService.createNewPayment(payment));

        Mockito.verify(paymentConverter, Mockito.times(0)).fromPaymentDtoToPayment(payment);
        Mockito.verify(paymentRepository, Mockito.times(0))
                .save(paymentConverter.fromPaymentDtoToPayment(payment));
    }
}