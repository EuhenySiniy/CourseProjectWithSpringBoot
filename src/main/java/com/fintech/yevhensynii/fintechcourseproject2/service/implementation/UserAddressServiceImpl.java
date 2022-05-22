package com.fintech.yevhensynii.fintechcourseproject2.service.implementation;

import com.fintech.yevhensynii.fintechcourseproject2.model.dto.UserAddressDto;
import com.fintech.yevhensynii.fintechcourseproject2.model.UserAddress;
import com.fintech.yevhensynii.fintechcourseproject2.repositories.UserAddressRepository;
import com.fintech.yevhensynii.fintechcourseproject2.service.UserAddressService;
import com.fintech.yevhensynii.fintechcourseproject2.service.converters.UserAddressConverter;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log4j2
public class UserAddressServiceImpl implements UserAddressService {
    private final UserAddressRepository userAddressRepository;
    private final UserAddressConverter userAddressConverter;

    @Override
    public String saveAddress(UserAddressDto userAddressDto){
        UserAddress addressFromDb = userAddressRepository.getUserAddressByCityAndStreetAndHouseNumAndApartmentNumAndUserId(
                userAddressDto.getCity(),
                userAddressDto.getStreet(),
                userAddressDto.getHouseNum(),
                userAddressDto.getApartmentNum(),
                userAddressDto.getUserId());
        if(addressFromDb!=null) {
            log.info("Address" + addressFromDb.getAddressId() + " is already exists");
            return "Address id " + addressFromDb.getAddressId() + ", already exists";
        }
        userAddressRepository.save(userAddressConverter.fromUserAddressDtoToUserAddress(userAddressDto));
        log.info(userAddressDto.getCity()
                + " " + userAddressDto.getStreet()
                + " " + userAddressDto.getHouseNum()
                + " has been saved");
        return "Address has been saved";
    }

    @Override
    public List<UserAddressDto> findAll() {
        return userAddressRepository.findAll()
                .stream()
                .map(userAddressConverter::fromUserAddressToUserAddressDto)
                .collect(Collectors.toList());
    }
}
