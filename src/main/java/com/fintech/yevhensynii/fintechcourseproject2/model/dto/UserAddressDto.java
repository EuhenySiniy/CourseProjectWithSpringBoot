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
public class UserAddressDto {
    private long addressId;

    @NotEmpty(message = "City should not be empty")
    @Size(min = 2, max = 30, message = "Too long or short city")
    @Pattern(regexp = "^[А-я]*(\\s([а-я]))?$",
            message = "Error in the name of the city, use Cyrillic, lack of numbers and symbols")
    private String city;

    @NotEmpty(message = "Street should not be empty")
    @Size(min = 2, max = 30, message = "Too long or short street")
    private String street;

    @NotEmpty(message = "House num should not be empty")
    @Size(min = 1, max = 5, message = "Too long or short house num")
    private String houseNum;

    @NotEmpty(message = "Apartment num should not be empty")
    @Size(max = 13, message = "Too long apartment num")
    private String apartmentNum;

    private long userId;
}
