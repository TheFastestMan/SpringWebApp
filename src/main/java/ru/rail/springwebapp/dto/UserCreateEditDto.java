package ru.rail.springwebapp.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import ru.rail.springwebapp.entity.Role;

import java.time.LocalDate;


public class UserCreateEditDto {
    private String username;
    private LocalDate birthDate;
    private String firstname;
    private String lastname;
    private Role role;
    private Integer companyId;
}
