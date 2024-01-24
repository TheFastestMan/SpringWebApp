package ru.rail.springwebapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rail.springwebapp.dto.LoginDto;
import ru.rail.springwebapp.dto.UserCreateEditDto;
import ru.rail.springwebapp.dto.UserReadDto;
import ru.rail.springwebapp.entity.User;
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
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    private UserCreateEditDto convertUserToUserDTO(User user) {
        UserCreateEditDto userDTO = modelMapper.map(user, UserCreateEditDto.class);
        userDTO.setUsername(user.getUsername());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setRole(user.getRole());
        userDTO.setCompanyId(user.getCompany().getId());
        return userDTO;
    }

    private UserReadDto convertUserToUserDTO2(User user) {
        UserReadDto userDTO = modelMapper.map(user, UserReadDto.class);
        userDTO.setUsername(user.getUsername());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setRole(user.getRole());
        return userDTO;
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
                        .password(password)
                        .username(username)
                        .build());
    }


    @Transactional
    public UserCreateEditDto register(UserCreateEditDto userCreateEditDto) {
        User user = modelMapper.map(userCreateEditDto, User.class);
        user = userRepository.save(user);
        return convertUserToUserDTO(user);
    }


    public Optional<UserReadDto> getById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        User user1 = user.get();
        UserReadDto userReadDto = convertUserToUserDTO2(user1);
        return Optional.of(userReadDto);

    }
}
