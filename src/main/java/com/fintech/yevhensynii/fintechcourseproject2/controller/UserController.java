package com.fintech.yevhensynii.fintechcourseproject2.controller;

import com.fintech.yevhensynii.fintechcourseproject2.model.dto.UserDto;
import com.fintech.yevhensynii.fintechcourseproject2.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Log4j2
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    public String saveUser(@RequestBody @Valid UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @GetMapping("/list-users")
    @ResponseBody
    public List<UserDto> showAllUsers() {
        log.info("Handling find all users request");
        return userService.findAll();
    }
}
