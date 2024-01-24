package ru.rail.springwebapp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.rail.springwebapp.dto.UserCreateEditDto;
import ru.rail.springwebapp.dto.UserReadDto;
import ru.rail.springwebapp.entity.Role;
import ru.rail.springwebapp.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping("/users")
    public String users(Model model) {
        List<UserReadDto> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/users";
    }

    @GetMapping("/user/{id}")
    public String findById(@PathVariable("id") Long userId, Model model) {
        Optional<UserReadDto> userDTO = userService.getById(userId);
        if (userDTO.isPresent()) {
            model.addAttribute("user", userDTO.get());
            return "user/user";
        } else {
            model.addAttribute("error", "User not found");
            return "errorPage";
        }
    }

    @PostMapping("/registration")
    public String create(@RequestParam("username") String username,
                         @RequestParam("birthDate") LocalDate birthDate,
                         @RequestParam("firstname") String firstname,
                         @RequestParam("lastname") String lastname,
                         @RequestParam("role") Role role,
                         @RequestParam("companyId") Integer companyId)
                          {
        UserCreateEditDto userCreateEditDto = UserCreateEditDto.builder()
                .username(username)
                .birthDate(birthDate)
                .firstname(firstname)
                .lastname(lastname)
                .role(role)
                .companyId(companyId)
                .build();

        userCreateEditDto = userService.register(userCreateEditDto);


        return "redirect:/user/" + userCreateEditDto.getId();
    }


//    @PostMapping("/{id}/update")
//    public String update(@PathVariable("id") Long id, @ModelAttribute UserCreateEditDto user) {
//        return "redirect:/user/{id}";
//    }
//
//    @PostMapping("/{id}/delete")
//    public String delete(@PathVariable("id") Long id) {
//       // userService.deleteUser(id);
//        return "redirect:/users";
//    }
}
