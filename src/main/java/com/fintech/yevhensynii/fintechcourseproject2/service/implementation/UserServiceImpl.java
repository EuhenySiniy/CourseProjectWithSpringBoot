package com.fintech.yevhensynii.fintechcourseproject2.service.implementation;

import com.fintech.yevhensynii.fintechcourseproject2.model.dto.UserDto;
import com.fintech.yevhensynii.fintechcourseproject2.model.User;
import com.fintech.yevhensynii.fintechcourseproject2.repositories.UserRepository;
import com.fintech.yevhensynii.fintechcourseproject2.service.UserService;
import com.fintech.yevhensynii.fintechcourseproject2.service.converters.UserConverter;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public String saveUser(UserDto userDto) {
        if (userRepository.getUserByEmail(userDto.getEmail()) != null) {
            log.info("User is already registered");
            return "User " + userDto.getEmail() + " already registered!";
        }
        userDto.setDateRegistration(Timestamp.from(new Timestamp(System.currentTimeMillis()).toInstant()));
        userRepository.save(userConverter.fromUserDtoToUser(userDto));
        log.info(userDto.getEmail() + " has been registered");
        return "User " + userDto.getEmail() + " has been registered";
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userConverter::fromUserToUserDto)
                .collect(Collectors.toList());
    }
}
