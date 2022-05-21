package com.fintech.yevhensynii.fintechcourseproject2.service.converters;

import com.fintech.yevhensynii.fintechcourseproject2.model.dto.UserAddressDto;
import com.fintech.yevhensynii.fintechcourseproject2.model.UserAddress;
import org.springframework.stereotype.Component;

@Component
public class UserAddressConverter {
    public UserAddress fromUserAddressDtoToUserAddress(UserAddressDto userAddressDto) {
        UserAddress userAddress = new UserAddress();
        userAddress.setAddressId(userAddressDto.getAddressId());
        userAddress.setCity(userAddressDto.getCity());
        userAddress.setStreet(userAddressDto.getStreet());
        userAddress.setHouseNum(userAddressDto.getHouseNum());
        userAddress.setApartmentNum(userAddressDto.getApartmentNum());
        userAddress.setUserId(userAddressDto.getUserId());
        return userAddress;
    }

    public UserAddressDto fromUserAddressToUserAddressDto(UserAddress userAddress) {
        return UserAddressDto.builder()
                .addressId(userAddress.getAddressId())
                .city(userAddress.getCity())
                .street(userAddress.getStreet())
                .houseNum(userAddress.getHouseNum())
                .apartmentNum(userAddress.getApartmentNum())
                .userId(userAddress.getUserId())
                .build();
    }
}
