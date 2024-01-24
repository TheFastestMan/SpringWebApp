package ru.rail.springwebapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.rail.springwebapp.dto.LoginDto;
import ru.rail.springwebapp.dto.UserCreateEditDto;
import ru.rail.springwebapp.entity.Role;
import ru.rail.springwebapp.service.UserService;

import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("web")
public class LoginController {
    @Autowired
    private final UserService userService;


    @GetMapping("/login")
    public String login() {
        return "web/login";
    }

    @PostMapping("/login")
    public String login1(@RequestParam("email") String email,
                         @RequestParam("password") String password) {
        Optional<LoginDto> userDTO = userService.login(email, password);

        if (userDTO.isPresent() && userDTO.get().getRole().equals("user")) {
            return "redirect:/users";
        }
        if (userDTO.isPresent() && userDTO.get().getRole().equals("admin")) {
            return "redirect:/user/" + userDTO.get().getId();
        } else
            return "web/login";

    }

}
