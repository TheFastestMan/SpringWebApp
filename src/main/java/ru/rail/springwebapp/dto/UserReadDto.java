package ru.rail.springwebapp.dto;

import ru.rail.springwebapp.entity.Role;

import java.time.LocalDate;

public class UserReadDto {
    private Long id;
    private String username;
    private LocalDate birthDate;
    private String firstname;
    private String lastname;
    private Role role;
    private CompanyReadDto company;
}
