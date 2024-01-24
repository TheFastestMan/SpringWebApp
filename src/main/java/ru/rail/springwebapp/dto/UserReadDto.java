package ru.rail.springwebapp.dto;

import lombok.*;
import ru.rail.springwebapp.entity.Role;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserReadDto {
    private Long id;
    private String username;
    private LocalDate birthDate;
    private String firstname;
    private String email;
    private String lastname;
    private Role role;
   private CompanyReadDto company;
}
