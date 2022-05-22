package com.fintech.yevhensynii.fintechcourseproject2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
public class PaymentDto {
    private long paymentId;

    @NotEmpty(message = "Credit card should not be empty")
    @Pattern(regexp = "^([4-5])[0-9]{15}?$",
            message = "Invalid credit card number")
    private String cardNum;

    @DecimalMin(value = "1", message = "sum should be greater than 1")
    private BigDecimal sum;

    private String status;

    private Timestamp dateCreate;

    private Timestamp dateStatus;

    private long sampleId;
}
