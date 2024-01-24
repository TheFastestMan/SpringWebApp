package ru.rail.springwebapp.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rail.springwebapp.dto.LoginDto;
import ru.rail.springwebapp.dto.UserCreateEditDto;
import ru.rail.springwebapp.dto.UserReadDto;
import ru.rail.springwebapp.entity.Company;
import ru.rail.springwebapp.entity.User;
import ru.rail.springwebapp.mapper.UserCreateEditMapper;
import ru.rail.springwebapp.mapper.UserReadMapper;
import ru.rail.springwebapp.repository.CompanyRepository;
import ru.rail.springwebapp.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final CompanyRepository companyRepository;
    @Autowired
    private final UserCreateEditMapper userCreateEditMapper;
    @Autowired
    private final UserReadMapper userReadMapper;

    public UserService(UserRepository userRepository, CompanyRepository companyRepository,
                       UserCreateEditMapper userCreateEditMapper, UserReadMapper userReadMapper) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;

        this.userCreateEditMapper = userCreateEditMapper;
        this.userReadMapper = userReadMapper;
    }

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(user -> UserReadDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .birthDate(user.getBirthDate())
                        .firstname(user.getFirstname())
                        .lastname(user.getLastname())
                        .role(user.getRole())
                        .build())
                .collect(Collectors.toList());
    }

    public Optional<LoginDto> login(String username, String password) {
        return userRepository.findByEmailAndPassword(username, password)
                .map(user -> LoginDto.builder()
                        .id(user.getId())
                        .password(password)
                        .username(username)
                        .role(user.getRole())
                        .build());
    }

    @Transactional
    public UserCreateEditDto register(UserCreateEditDto userCreateEditDto) {
        User user = userCreateEditMapper.mapFrom(userCreateEditDto);

        if (userCreateEditDto.getCompanyId() != null) {
            Company company = companyRepository.findById(userCreateEditDto.getCompanyId())
                    .orElseThrow(() -> new EntityNotFoundException("Company not found with id: " + userCreateEditDto.getCompanyId()));
            user.setCompany(company);
        } else {
            user.setCompany(null);
        }

        user = userRepository.save(user);
        return userCreateEditMapper.mapTo(user);

    }

    public Optional<UserReadDto> getById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        User user1 = user.get();
        UserReadDto userReadDto = userReadMapper.mapFrom(user1);
        return Optional.of(userReadDto);

    }
}
