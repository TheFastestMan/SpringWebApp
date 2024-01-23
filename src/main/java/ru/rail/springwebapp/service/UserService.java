package ru.rail.springwebapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rail.springwebapp.dto.UserDTO;
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

    private UserDTO convertUserToUserDTO(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(user -> UserDTO.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build())
                .collect(Collectors.toList());

    }

    public Optional<UserDTO> login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .map(user -> UserDTO.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build());
    }


    @Transactional
    public UserDTO register(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user = userRepository.save(user);
        return convertUserToUserDTO(user);
    }


    public Optional<UserDTO> getById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        User user1 = user.get();
        UserDTO userDTO = convertUserToUserDTO(user1);
        return Optional.of(userDTO);

    }
}
