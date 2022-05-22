package com.fintech.yevhensynii.fintechcourseproject2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
public class SampleDto {
    private long sampleId;

    @NotEmpty(message = "Sample name should not be empty")
    @Size(min = 3, max = 100, message = "Too long or short sample name")
    private String sampleName;

    @NotEmpty(message = "Iban should not be empty")
    @Size(min = 29, max = 29, message = "iban contains 29 characters")
    @Pattern(regexp = "^[A-Z]{2}[0-9]{27}?$",
            message = "Error in the iban. Correct: {UA + 27 nums}")
    private String iban;

    @NotEmpty(message = "OKPO should not be empty")
    @Size(min = 8, max = 8, message = "OKPO contains 8 characters")
    private String okpo;

    @NotEmpty(message = "Appointment should not be empty")
    @Size(max = 160, message = "Too long appointment")
    private String appointment;

    private long addressId;
}
