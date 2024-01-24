package ru.rail.springwebapp.dto;

import lombok.*;
import org.springframework.stereotype.Component;
import ru.rail.springwebapp.entity.Role;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private Long id;
   private String username;
    private String password;
    private Role role;
}
