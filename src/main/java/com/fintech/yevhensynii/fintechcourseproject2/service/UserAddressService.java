package com.fintech.yevhensynii.fintechcourseproject2.service;

import com.fintech.yevhensynii.fintechcourseproject2.model.dto.UserAddressDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserAddressService {
    String saveAddress(UserAddressDto userAddressDto);

    List<UserAddressDto> findAll();
}
