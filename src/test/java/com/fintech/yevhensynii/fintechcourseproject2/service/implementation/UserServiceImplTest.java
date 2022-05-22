package com.fintech.yevhensynii.fintechcourseproject2.service.implementation;

import com.fintech.yevhensynii.fintechcourseproject2.model.User;
import com.fintech.yevhensynii.fintechcourseproject2.model.dto.UserDto;
import com.fintech.yevhensynii.fintechcourseproject2.repositories.UserRepository;
import com.fintech.yevhensynii.fintechcourseproject2.service.UserService;
import com.fintech.yevhensynii.fintechcourseproject2.service.converters.UserConverter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserConverter userConverter;

    @Test
    void saveUserSuccessfully() {
        UserDto user = new UserDto(
                1,
                "Иван",
                "Иванович",
                "Иванов",
                "mail@mail.com",
                "+380731231212",
                null
        );
        userService.saveUser(user);

        Assertions.assertTrue(true, "User " + user.getEmail() + " has been registered");
        Assertions.assertNotNull(user.getDateRegistration());

        Mockito.verify(userRepository, Mockito.times(1)).getUserByEmail(user.getEmail());
        Mockito.verify(userConverter, Mockito.times(1)).fromUserDtoToUser(user);
        Mockito.verify(userRepository, Mockito.times(1)).save(userConverter.fromUserDtoToUser(user));
    }

    @Test
    void saveUserFailed() {
        UserDto user = new UserDto(
                1L,
                "Иван",
                "Иванович",
                "Иванов",
                "mail@mail.com",
                "+380731231212",
                null
        );

        Mockito.doReturn(new User())
                        .when(userRepository)
                        .getUserByEmail("mail@mail.com");

        userService.saveUser(user);

        Assertions.assertTrue(true, "User " + user.getEmail() + " already registered!");

        Mockito.verify(userConverter, Mockito.times(0)).fromUserDtoToUser(user);
        Mockito.verify(userRepository, Mockito.times(0)).save(userConverter.fromUserDtoToUser(user));
    }
}