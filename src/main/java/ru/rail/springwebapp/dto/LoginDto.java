package ru.rail.springwebapp.dto;

import lombok.Value;
import org.springframework.stereotype.Component;

@Value
public class LoginDto {
    String username;
    String password;
}
