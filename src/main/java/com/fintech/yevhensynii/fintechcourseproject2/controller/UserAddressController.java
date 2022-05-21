package com.fintech.yevhensynii.fintechcourseproject2.controller;

import com.fintech.yevhensynii.fintechcourseproject2.model.dto.UserAddressDto;
import com.fintech.yevhensynii.fintechcourseproject2.service.UserAddressService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/address")
@AllArgsConstructor
@Log4j2
public class UserAddressController {
    private final UserAddressService userAddressService;

    @PostMapping("/save")
    public String saveAddress(@RequestBody @Valid UserAddressDto userAddressDto) {
        return userAddressService.saveAddress(userAddressDto);
    }

    @GetMapping("/list-address")
    public List<UserAddressDto> showAllAddress() {
        return userAddressService.findAll();
    }
}
