package ru.rail.springwebapp.mapper;

import org.springframework.stereotype.Component;
import ru.rail.springwebapp.dto.UserReadDto;
import ru.rail.springwebapp.entity.User;
@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {
    private final CompanyReadMapper companyReadMapper;

    public UserReadMapper(CompanyReadMapper companyReadMapper) {
        this.companyReadMapper = companyReadMapper;
    }

    @Override
    public UserReadDto mapFrom(User user) {
        return UserReadDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .birthDate(user.getBirthDate())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .role(user.getRole())
                .company(companyReadMapper.mapFrom(user.getCompany()))
                .build();
    }

    @Override
    public User mapTo(UserReadDto dto) {
        return null;
    }
}