package ru.rail.springwebapp.dto;

import lombok.*;
import org.springframework.stereotype.Component;
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
   private String username;
    private String password;
}
