package com.fintech.yevhensynii.fintechcourseproject2.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.sql.Timestamp;

@Data
@Builder
public class UserDto {
    private long userId;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Too long or short name")
    @Pattern(regexp = "^[А-я]*(\\s([а-я]))?$",
            message = "Error in the first name, use Cyrillic, lack of numbers and symbols")
    private String firstName;

    @NotEmpty(message = "Middle name should not be empty")
    @Size(min = 2, max = 30, message = "Too long or short middle name")
    @Pattern(regexp = "^[А-я]*(\\s([а-я]))?$",
            message = "Error in the middle name, use Cyrillic, lack of numbers and symbols")
    private String middleName;

    @NotEmpty(message = "Last name should not be empty")
    @Size(min = 2, max = 30, message = "Too long or short last name")
    @Pattern(regexp = "^[А-я]*(\\s([а-я]))?$",
            message = "Error in the last name, use Cyrillic, lack of numbers and symbols")
    private String lastName;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email not valid. Example: 'mail@gmail.com'")
    @Size(max = 64, message = "Too long email")
    private String email;

    @NotEmpty(message = "Phone should be not empty")
    @Size(max = 15, message = "Too long phone number")
    @Pattern(regexp = "^((\\+?380)([0-9]{9}))$",
            message = "Bad phone number")
    private String tel;

    private Timestamp dateRegistration;
}
