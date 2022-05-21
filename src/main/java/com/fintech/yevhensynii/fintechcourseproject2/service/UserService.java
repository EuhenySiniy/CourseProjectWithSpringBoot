package com.fintech.yevhensynii.fintechcourseproject2.service;

import com.fintech.yevhensynii.fintechcourseproject2.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    String saveUser(UserDto userDto);

    List<UserDto> findAll();
}
