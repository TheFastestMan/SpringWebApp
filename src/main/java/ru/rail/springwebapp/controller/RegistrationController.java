package ru.rail.springwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.rail.springwebapp.dto.UserDTO;
import ru.rail.springwebapp.service.UserService;

@Controller
public class RegistrationController {
    @Autowired
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String login() {
        return "web/registration";
    }

    @PostMapping("/registration")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("role") String role) {
        UserDTO newUserDTO = UserDTO.builder()
                .username(username)
                .email(email)
                .password(password)
                .role(role)
                .build();

        newUserDTO = userService.register(newUserDTO);

        return "redirect:/web/user/" + newUserDTO.getId();
    }

}
