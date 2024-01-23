package ru.rail.springwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import ru.rail.springwebapp.dto.UserDTO;
import ru.rail.springwebapp.service.UserService;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("web")
public class LoginController {
    @Autowired
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<UserDTO> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/users";
    }


    @GetMapping("/user/{id}")
    public String user(@PathVariable("id") Long userId, Model model) {
        Optional<UserDTO> userDTO = userService.getById(userId);
        if (userDTO.isPresent()) {
            model.addAttribute("user", userDTO.get());
            return "user/user";
        } else {
            model.addAttribute("error", "User not found");
            return "errorPage";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "web/login";
    }

    @PostMapping("/login")
    public String login1(@RequestParam("email") String email,
                         @RequestParam("password") String password) {
        Optional<UserDTO> userDTO = userService.login(email, password);

        if (userDTO.isPresent() && userDTO.get().getRole().equals("user")) {
            return "redirect:/web/users";
        }
        if (userDTO.isPresent() && userDTO.get().getRole().equals("admin")) {
            return "redirect:/web/user/" + userDTO.get().getId();
        } else
            return "web/login";

    }

}
