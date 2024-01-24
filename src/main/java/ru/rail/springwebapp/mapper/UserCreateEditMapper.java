package ru.rail.springwebapp.mapper;

import org.springframework.stereotype.Component;
import ru.rail.springwebapp.dto.CompanyReadDto;
import ru.rail.springwebapp.dto.UserCreateEditDto;
import ru.rail.springwebapp.dto.UserReadDto;
import ru.rail.springwebapp.entity.Role;
import ru.rail.springwebapp.entity.User;

import java.time.LocalDate;
@Component
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {
    private final CompanyReadMapper companyReadMapper;

    public UserCreateEditMapper(CompanyReadMapper companyReadMapper) {
        this.companyReadMapper = companyReadMapper;
    }

    @Override
    public User mapFrom(UserCreateEditDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setBirthDate(dto.getBirthDate());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }

    @Override
    public UserCreateEditDto mapTo(User user) {
        if (user == null) {
            return null;
        }
        return UserCreateEditDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .birthDate(user.getBirthDate())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .companyId(user.getCompany().getId())
                .build();
    }
}