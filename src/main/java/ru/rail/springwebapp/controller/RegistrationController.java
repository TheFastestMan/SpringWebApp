package ru.rail.springwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.rail.springwebapp.entity.Role;
import ru.rail.springwebapp.service.CompanyService;

@Controller
public class RegistrationController {
    @Autowired
    private final CompanyService companyService;

    public RegistrationController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("roles", Role.values());
        model.addAttribute("companies", companyService.getAll());
        return "web/registration";
    }


}
