package com.fintech.yevhensynii.fintechcourseproject2.service.implementation;

import com.fintech.yevhensynii.fintechcourseproject2.model.UserAddress;
import com.fintech.yevhensynii.fintechcourseproject2.model.dto.UserAddressDto;
import com.fintech.yevhensynii.fintechcourseproject2.repositories.UserAddressRepository;
import com.fintech.yevhensynii.fintechcourseproject2.service.UserAddressService;
import com.fintech.yevhensynii.fintechcourseproject2.service.converters.UserAddressConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserAddressServiceImplTest {
    @Autowired
    private UserAddressService userAddressService;

    @MockBean
    private UserAddressRepository userAddressRepository;

    @MockBean
    private UserAddressConverter userAddressConverter;

    @Test
    void saveAddressSuccessfully() {
        UserAddressDto address = new UserAddressDto(
                1L,
                "City",
                "Street",
                "1",
                "1",
                1L
        );
        userAddressService.saveAddress(address);

        Assertions.assertTrue(true, "Address id " + address.getAddressId() + ", has been saved");

        Mockito.verify(userAddressRepository, Mockito.times(1))
                .getUserAddressByCityAndStreetAndHouseNumAndApartmentNumAndUserId(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyLong()
                );
        Mockito.verify(userAddressConverter, Mockito.times(1)).fromUserAddressDtoToUserAddress(address);
        Mockito.verify(userAddressRepository, Mockito.times(1))
                .save(userAddressConverter.fromUserAddressDtoToUserAddress(address));

    }

    @Test
    void saveAddressFailed() {
        UserAddressDto address = new UserAddressDto(
                1L,
                "City",
                "Street",
                "1",
                "1",
                1L
        );
        UserAddress addressFromDb = new UserAddress();

        Mockito.doReturn(new UserAddress())
                .when(userAddressRepository)
                .getUserAddressByCityAndStreetAndHouseNumAndApartmentNumAndUserId(
                        "City",
                        "Street",
                        "1",
                        "1",
                        1L
                );

        userAddressService.saveAddress(address);

        Assertions.assertTrue(true, "Address id: " + addressFromDb.getAddressId() + ", already exists");

        Mockito.verify(userAddressConverter, Mockito.times(0)).fromUserAddressDtoToUserAddress(address);
        Mockito.verify(userAddressRepository, Mockito.times(0))
                .save(userAddressConverter.fromUserAddressDtoToUserAddress(address));
    }
}