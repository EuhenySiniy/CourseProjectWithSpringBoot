package com.fintech.yevhensynii.fintechcourseproject2.service.converters;

import com.fintech.yevhensynii.fintechcourseproject2.model.dto.UserDto;
import com.fintech.yevhensynii.fintechcourseproject2.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User fromUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setTel(userDto.getTel());
        user.setDateRegistration(userDto.getDateRegistration());
        return user;
    }

    public UserDto fromUserToUserDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .tel(user.getTel())
                .dateRegistration(user.getDateRegistration())
                .build();
    }
}
