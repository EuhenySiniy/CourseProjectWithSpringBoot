package com.fintech.yevhensynii.fintechcourseproject2.controller;

import com.fintech.yevhensynii.fintechcourseproject2.model.dto.PaymentDto;
import com.fintech.yevhensynii.fintechcourseproject2.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
@Log4j2
public class PaymentController {
    private final PaymentService paymentService;

    @RequestMapping("/create")
    public String createPayment(@RequestBody @Valid PaymentDto paymentDto) {
        return paymentService.createNewPayment(paymentDto);
    }

    @GetMapping("/list-payments")
    public List<PaymentDto> showAllPayment() {
        log.info("Handling find all payments request");
        return paymentService.showAllPayments();
    }
}
