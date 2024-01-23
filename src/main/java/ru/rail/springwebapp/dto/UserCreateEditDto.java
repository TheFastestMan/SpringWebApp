package ru.rail.springwebapp.dto;

import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;
import ru.rail.springwebapp.entity.Role;

import java.time.LocalDate;

@Value
public class UserCreateEditDto {
    String username;
    LocalDate birthDate;
    String firstname;
    String lastname;
    Role role;
    Integer companyId;
}
